package life.grass.kotlinsandbox.hotbar

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import life.grass.kotlinsandbox.extension.displayName
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author BlackBracken
 */
class PhantomHotbar(val triggerMaterial: Material, // TODO: check more strictly
                    private val slotMap: Map<Int, PhantomHotbarSlot>) {

    companion object {
        private val defaultSlotIcon = ItemStack(Material.BLACK_STAINED_GLASS_PANE).apply {
            displayName = " "
        }
        private val protocolManager: ProtocolManager = ProtocolLibrary.getProtocolManager()
    }

    fun openBy(player: Player) {
        val packet = protocolManager.createPacket(PacketType.Play.Server.WINDOW_ITEMS)

        packet.itemListModifier.writeDefaults()

        val phantomInventory = mutableListOf<ItemStack>()
        (0 until 36).forEach { _ -> phantomInventory += ItemStack(Material.AIR) }
        (0 until 9).forEach { index -> phantomInventory += slotMap[index]?.icon ?: defaultSlotIcon }

        packet.itemListModifier.write(0, phantomInventory)

        protocolManager.sendServerPacket(player, packet)
    }

    fun chooseBy(player: Player, slot: Int) {
        val hotbarSlot = slotMap[slot] ?: return

        hotbarSlot.action(player)
    }

    fun closeBy(player: Player) {
        player.updateInventory()
    }

}