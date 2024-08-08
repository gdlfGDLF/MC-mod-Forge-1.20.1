package updata;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CustomSword extends SwordItem {

    private final String tooltipKey;
    private final int defaultColor;
    private final Map<Enchantment, Integer> enchantments;

    public CustomSword(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties,
                       String tooltipKey, int defaultColor, Map<Enchantment, Integer> enchantments) {
        super(tier, attackDamage, attackSpeed, properties);
        this.tooltipKey = tooltipKey;
        this.defaultColor = defaultColor != 0 ? defaultColor : 0xFFFFFF; // 默认颜色为白色
        this.enchantments = enchantments;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // 添加自定义描述
        Component customDescription = Component.translatable(tooltipKey);
        Style style = Style.EMPTY.withColor(TextColor.fromRgb(defaultColor));
        Component styledDescription = customDescription.copy().setStyle(style);
        tooltip.add(styledDescription);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // 确保物品具有附魔效果时显示发光效果
        return true;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        applyEnchantments(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        // 确保物品默认具有附魔效果
        return false; // 阻止用户附加额外的附魔
    }

    @Override
    public ItemStack getDefaultInstance() {
        // 返回一个附有预设附魔的默认物品实例
        ItemStack stack = new ItemStack(this);
        applyEnchantments(stack);
        return stack;
    }

    private void applyEnchantments(ItemStack stack) {
        // 确保在物品实例创建时应用附魔
        if (!stack.isEnchanted()) {
            EnchantmentHelper.setEnchantments(enchantments, stack);
        }
    }
}
