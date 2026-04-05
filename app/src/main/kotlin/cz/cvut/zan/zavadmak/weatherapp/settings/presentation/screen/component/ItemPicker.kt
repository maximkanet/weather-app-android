package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R

@Composable
fun <T> ItemPicker(
    current: T,
    items: List<T>,
    comparator: (T, T) -> Boolean,
    toStringMapper: (T) -> String,
    onItemSelect: (T) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = { expanded = true })
            .background(
                color = Color(0x28D5D5D5),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = toStringMapper(current),
            fontSize = 12.sp
        )
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_drop_down_24),
            contentDescription = "",
            modifier = Modifier.size(18.dp),
            tint = Color(0x9E7F7F7F),
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(toStringMapper(item))
                        if (comparator(item, current)) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_check_24),
                                contentDescription = "",
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                },
                onClick = {
                    expanded = false
                    onItemSelect(item)
                }
            )
        }
    }

}