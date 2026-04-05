package cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM location ORDER BY placeId ASC")
    suspend fun getLocations(): List<LocationEntity>

    @Query("SELECT * FROM location WHERE location.placeId = :id")
    suspend fun getLocationById(id: Long): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)

}