package life.grass.kotlinsandbox.house

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

class HouseContainer {
    var houseSet: MutableSet<House> = mutableSetOf()

    fun onClickAction(location: Location?, player: Player) {
        if (location == null) {
            return
        }
        when (player.inventory.itemInMainHand.type) {
            Material.DIAMOND_PICKAXE -> {
                if (isBuildable(location, player.uniqueId)) createHouse(location, player.uniqueId)
            }
            else -> (Unit)
        }
    }

    fun createHouse(location: Location, residentUUID: UUID) {
        houseSet.add(House(location, residentUUID))
    }

    fun isBuildable(location: Location, builderUUID: UUID): Boolean {
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world
        return houseSet.filter {
            it.residentUUID == builderUUID ||
                    (Math.abs(it.location.x - x) < 10 &&
                            Math.abs(it.location.y - y) < 10 &&
                            Math.abs(it.location.z - z) < 10 &&
                            it.location.world == world)
        }.isEmpty()
    }
}
