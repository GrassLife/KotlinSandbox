package life.grass.kotlinsandbox.listener

import life.grass.kotlinsandbox.hotbar.PhantomHotbar
import life.grass.kotlinsandbox.hotbar.closePhantomHotbar
import life.grass.kotlinsandbox.hotbar.isOpeningHotbarSlot
import life.grass.kotlinsandbox.hotbar.openPhantomHotbar
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent

/**
 * @author BlackBracken
 */
class HotbarListener(private val registeredPhantomHotbarList: List<PhantomHotbar>) : Listener {

    @EventHandler
    fun onSwapHandItems(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        val mainHandMaterial = event.mainHandItem?.type ?: return
        val openedPhantomHotbar = registeredPhantomHotbarList
                .find { hotbar -> hotbar.triggerMaterial == mainHandMaterial }
                ?: return

        if (player.isOpeningHotbarSlot()) {
            event.isCancelled = true

            player.openPhantomHotbar(openedPhantomHotbar)
        }
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        (event.player as? Player)?.closePhantomHotbar()
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.player.closeInventory()
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        event.entity.closeInventory()
    }

}