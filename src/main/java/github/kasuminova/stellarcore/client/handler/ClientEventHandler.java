package github.kasuminova.stellarcore.client.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientEventHandler {
    public static final ClientEventHandler INSTANCE = new ClientEventHandler();

    private long clientTick = 0;

    private ClientEventHandler() {
    }

}
