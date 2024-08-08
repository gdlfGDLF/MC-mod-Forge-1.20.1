package updata;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.enchantment.Enchantments;
import java.util.Map;
public class RegisterList {
    //itme
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, updata.MODID);
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("updata_gold_apple",
            () -> new InfiniteUseItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEat()
                            .nutrition(8)
                            .saturationMod(10f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 2), 1.0F) // 生命恢复 III 持续 15 秒（300 tick）
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 6000, 14), 1.0F) // 临时生命值 15 点（6000 tick，5 分钟）
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 2), 1.0F) // 抗性提升 III 持续 5 分钟
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F) // 抗火 5 分钟
                            .build())
                    .rarity(Rarity.EPIC)
                    .stacksTo(1),
                    "item.updata.updata_gold_apple.tooltip",
                    0xFF0000
            )
    );


    public static final RegistryObject<Item> CUSTOM_SWORD = ITEMS.register("up_sword_raresword",
            () -> new CustomSword(ModItemTier.updata_Rare_sword, 7, -2.4F,
                    new Item.Properties(),
                    "item.updata.up_sword_raresword.tooltip", 0xFF0000,
                    Map.of(
                            Enchantments.SHARPNESS, 5,
                            Enchantments.MENDING, 1,
                            Enchantments.MOB_LOOTING, 5 // 这里指定附魔及其等级
                    )
            )
    );


    //tablist
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, updata.MODID);
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("updata_food_tablist", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            })
            .title(Component.translatable(("itemGroup.updata.updata_food_tablist")))
            .build());


    public static final RegistryObject<CreativeModeTab> updataToolTablist = CREATIVE_MODE_TABS.register("updata_tool_tablist", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> CUSTOM_SWORD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CUSTOM_SWORD.get().getDefaultInstance()); // 使用默认实例
            })
            .title(Component.translatable("itemGroup.updata.updata_tool_tablist"))
            .build());



}
