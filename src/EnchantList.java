/*
 * EnchantList.java
 * An enchantlist class for use with the MazeDQ program
 * 
 * @author Jack Manges
 */

 import java.util.*;
 
 public class EnchantList{ 

    private Random randGen = new Random();
    private ArrayList<Enchantment> enchants = new ArrayList<Enchantment>();

    public EnchantList(){
        enchants.add(new Enchantment("Flaming", 2));
        enchants.add(new Enchantment("Electric", 4));
        enchants.add(new Enchantment("Freezing", 2));
        enchants.add(new Enchantment("Time-shifting", 6));
    }

    public Enchantment getEnchantment(String name){

        int index = 0;
        for (int i = 0; i < enchants.size(); i++){

            if (name.equalsIgnoreCase(enchants.get(i).getName()))
            {
                index = i;
            }
        }
        return enchants.get(index);
    }

    public Enchantment getRandomEnchantment(){
        int n = randGen.nextInt(enchants.size());
        return enchants.get(n);
      }
}