/*
 * Weapon.java
 * A weapon class for use with the DunCraMaz program
 * 
 * @author Jack Manges
 */

import java.util.Random;
 
public abstract class Weapon
{
    protected String name = "";
    protected int damage;
    protected Enchantment enchantment;
    protected Boolean enchanted;
    static protected EnchantList enchantList;

    public Weapon(String name, Boolean enchant)
    {
        this.name = name;
        enchantList = new EnchantList();

        if (enchant == true) {
            enchanted = true;
            enchantment = enchantList.getRandomEnchantment();
            this.name = enchantment.getName() + " " + this.name;
        }
        else {enchanted = false;}
    }

    public int damageRoll()
    {
        Random randGen = new Random();
        int n = randGen.nextInt(damage) + 1;
        if (enchanted == true) {
            n += enchantment.damageRoll();
        }
        return n;
    }

    public void addEnchantment(Enchantment enchantment)
    {
        this.enchantment = enchantment;
        this.name = enchantment.getName() + " " + this.name;
        enchanted = true;
    }

    public String getName(){return name;}
    public int getDamage(){return damage;}
    public Enchantment getEnchantment(){return enchantment;}

    public String toString()
    {
        if (this.enchanted == true) {
            return this.name + "\tDamage: " + (this.damage + this.enchantment.getDamage()); 
        }
        else {
            return this.name + "\tDamage: " + this.damage;
        }
    }
}