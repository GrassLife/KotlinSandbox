package life.grass.kotlinsandbox.listener

import life.grass.kotlinsandbox.hotbar.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent

/**
 * @author BlackBracken
 */
class HotbarListener(private val registeredPhantomHotbarList: List<PhantomHotbar>) : Listener {

    @EventHandler
    fun onSwapHandItems(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        val mainHandMaterial = event.offHandItem?.type ?: return
        val openedPhantomHotbar = registeredPhantomHotbarList
                .find { hotbar -> hotbar.triggerMaterial == mainHandMaterial }
                ?: return

        if (!player.isOpeningHotbarSlot()) {
            event.isCancelled = true

            player.openPhantomHotbar(openedPhantomHotbar)
        }
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        // TODO: Eでのインベントリ開閉がキャッチできない
        (event.player as? Player)?.closePhantomHotbar()
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val hotbarSlotIndex = player.inventory.heldItemSlot

        if (player.isOpeningHotbarSlot()) {
            player.choosePhantomHotbarSlot(hotbarSlotIndex)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.player.closePhantomHotbar()
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        event.entity.closePhantomHotbar()
    }

}