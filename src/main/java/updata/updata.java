package updata;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(updata.MODID)
public class updata {
    public static final String MODID = "updata";

    public updata() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegisterList.ITEMS.register(modEventBus);
        RegisterList.CREATIVE_MODE_TABS.register(modEventBus);
    }


}
