package life.grass.kotlinsandbox.listener

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * @author BlackBracken
 */
class HotkeyListener(private val protocolManager: ProtocolManager) : Listener {

    @EventHandler
    fun onClickBone(event: PlayerInteractEvent) {
        val player = event.player

        if (player.inventory.itemInMainHand.type != Material.BONE) {
            player.updateInventory()
            return
        }

        val packet = protocolManager.createPacket(PacketType.Play.Server.WINDOW_ITEMS)

        packet.itemListModifier.writeDefaults()

        val phantomInventory = mutableListOf<ItemStack>()
        (0 until 36).forEach { _ -> phantomInventory += ItemStack(Material.AIR) }
        (0 until 9).forEach { _ -> phantomInventory += ItemStack(Material.BARRIER) }

        packet.itemListModifier.write(0, phantomInventory)

        protocolManager.sendServerPacket(player, packet)
        player.sendMessage("Sent you the packet")
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
    }

}