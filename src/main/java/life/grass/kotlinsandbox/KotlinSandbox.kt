package life.grass.kotlinsandbox

import life.grass.kotlinsandbox.hotbar.PhantomHotbar
import life.grass.kotlinsandbox.hotbar.PhantomHotbarSlot
import life.grass.kotlinsandbox.listener.HotbarListener
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author BlackBracken
 */
class KotlinSandbox : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        println("Hello, World!")

        val registeredPhantomHotbarList = listOf(
                PhantomHotbar(Material.BONE, mapOf(
                        0 to PhantomHotbarSlot(ItemStack(Material.IRON_SWORD)) { player -> player.sendMessage("Chose a sword!") },
                        1 to PhantomHotbarSlot(ItemStack(Material.SHIELD)) { player -> player.sendMessage("Chose a shield!") },
                        8 to PhantomHotbarSlot(ItemStack(Material.BARRIER)) { }
                )),
                PhantomHotbar(Material.STICK, mapOf(
                        0 to PhantomHotbarSlot(ItemStack(Material.GOLDEN_PICKAXE)) { player -> player.sendMessage("Chose a pickaxe!") },
                        1 to PhantomHotbarSlot(ItemStack(Material.HEART_OF_THE_SEA)) { player -> player.sendMessage("Chose a ball!") },
                        8 to PhantomHotbarSlot(ItemStack(Material.BARRIER)) { }
                ))
        )

        server.pluginManager.registerEvents(HotbarListener(registeredPhantomHotbarList), this)
    }

}