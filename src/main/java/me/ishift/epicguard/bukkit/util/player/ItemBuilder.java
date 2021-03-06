package me.ishift.epicguard.bukkit.util.player;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {
    private final short data;
    private final List<String> lore;
    private final HashMap<Enchantment, Integer> enchants;
    private Material mat;
    private int amount;
    private String title;
    private Color color;

    public ItemBuilder(final Material mat) {
        this(mat, 1);
    }

    public ItemBuilder(final Material mat, final int amount) {
        this(mat, amount, (short) 0);
    }

    public ItemBuilder(final Material mat, final short data) {
        this(mat, 1, data);
    }

    public ItemBuilder(final Material mat, final int amount, final short data) {
        this.title = null;
        this.lore = new ArrayList<>();
        this.enchants = new HashMap<>();
        this.mat = mat;
        this.amount = amount;
        this.data = data;
    }

    public ItemBuilder setTitle(final String title) {
        this.title = ChatUtil.fix(title);
        return this;
    }

    public ItemBuilder addLores(final List<String> lores) {
        this.lore.addAll(lores);
        return this;
    }

    public ItemBuilder addLore(final String lore) {
        this.lore.add(ChatUtil.fix(lore));
        return this;
    }

    public ItemBuilder addEnchantment(final Enchantment enchant, final int level) {
        this.enchants.remove(enchant);
        this.enchants.put(enchant, level);
        return this;
    }

    public ItemBuilder setColor(final Color color) {
        if (!this.mat.name().contains("LEATHER_")) {
            throw new IllegalArgumentException("Can't set color for NON-LEATHER material.");
        }
        this.color = color;
        return this;
    }

    public ItemStack build() {
        final Material mat = this.mat;
        if (mat == null) {
            Bukkit.getLogger().warning("Material is null.");
        }
        final ItemStack item = new ItemStack(this.mat, this.amount, this.data);
        final ItemMeta meta = item.getItemMeta();
        if (this.title != null) {
            meta.setDisplayName(this.title);
        }
        if (!this.lore.isEmpty()) {
            meta.setLore(this.lore);
        }
        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(this.color);
        }
        item.setItemMeta(meta);
        item.addUnsafeEnchantments(this.enchants);
        return item;
    }
}