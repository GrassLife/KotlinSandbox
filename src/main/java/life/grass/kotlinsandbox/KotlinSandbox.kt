package life.grass.kotlinsandbox

import com.comphenix.protocol.ProtocolLibrary
import life.grass.kotlinsandbox.listener.HotkeyListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author BlackBracken
 */
class KotlinSandbox : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        println("Hello, World!")

        val protocolManager = ProtocolLibrary.getProtocolManager()

        server.pluginManager.registerEvents(HotkeyListener(protocolManager), this)
    }

}