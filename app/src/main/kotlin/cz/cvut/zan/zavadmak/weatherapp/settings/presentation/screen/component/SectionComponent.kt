package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionComponent(
    name: String,
    contentSpacing: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    content: LazyListScope.() -> Unit,
//    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = name,
            fontSize = 18.sp
        )
        LazyColumn(
            verticalArrangement = contentSpacing
        ) {
            content()
        }
    }
}