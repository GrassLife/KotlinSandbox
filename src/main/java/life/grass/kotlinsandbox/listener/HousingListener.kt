package life.grass.kotlinsandbox.listener

import life.grass.kotlinsandbox.house.HouseContainer
import org.bukkit.block.Banner
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class HousingListener(private var houseContainer: HouseContainer) : Listener {

    @EventHandler
    fun onRightClickFlag(event: PlayerInteractEvent) {
        if (event.hand == EquipmentSlot.HAND && event.action == Action.RIGHT_CLICK_BLOCK && event.clickedBlock?.state is Banner) {
            houseContainer.onClickAction(event.clickedBlock?.location, event.player)
        }
    }
}
