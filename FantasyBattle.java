import java.lang.Exception;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

abstract class Character{
    String name;
    int health;
    int attack_power;
    int defense;
    double speed;

    public Character(String nm,int health,int att_pwr,int defense,double speed){
        this.name=nm;
        this.health=health;
        this.attack_power= att_pwr;
        this.defense=defense;
        this.speed=speed;

    }
    public abstract void attack(Character target);{

    }
    public void takeDamage(int amount){
        int damage = amount - defense;
        if (damage<1) {
            damage = 1;
        }
        health -= damage;
        if(health < 0 ) {
            health = 0;
        }
        System.out.println( name + " take " +  damage + "damage. Health left : "+health);

    }
    public boolean is_alive(){
        return health > 0;

    }

}

class Warrior extends Character{

    int rage;

    public Warrior(String name){
        super(name, 150,30,10,5);
        this.rage = 0;
    }

    @Override
    public void attack(Character target){
        int currentAttack = attack_power;
        if(health<45){
            System.out.println(name + " enters Berserk mode.Attack power doubled.");
            currentAttack *=2;
        }

        if(rage == 100){
            System.out.println(name + "rage strike.More damage");
            currentAttack +=15;
            rage = 0;
        }
        System.out.println( name + " attacks with sword");
        target.takeDamage(currentAttack);
    }

}

class Mage extends Character{

    int mana;
     public Mage(String name){
         super(name,100,40,5,7);
         this.mana=50;
     }

     @Override
     public void attack(Character target){
         try {
             if (mana < 10) {
                 throw new Exception(name + " has no mana left");
             }
             System.out.println(name + "casts Fireball");
             target.takeDamage(attack_power + 10);
             mana -= 10;
             this.takeDamage(5);
         }catch(Exception e){
             System.out.println(e.getMessage());
             System.out.println(name + " attacks weakly using staff.");
             target.takeDamage(attack_power/2);

         }
     }
}

class Archer extends Character{
    double critical_chance;
    public Archer(String name){
        super(name,120,25,7,10);
        this.critical_chance=0.3;
    }

    @Override
    public void attack(Character target){
        Random rand = new Random();
        int currentAttack= attack_power;
        if (rand.nextDouble() < critical_chance){
            System.out.println(name + "lands a critical hit");
            currentAttack *=2;
        }else{
            System.out.println(name + " shoots an arrow");
        }
        target.takeDamage(currentAttack);
    }
}

class Battle{
    public static void startBattle(Character player,Character enemy){
        try{
            System.out.println("Game starts : " +player.name +" between" + enemy.name);

            Character first;
            Character second;

            if(player.speed > enemy.speed){
                first = player;
                second = enemy;
            }else if (enemy.speed > player.speed){
                first = enemy;
                second = player;
            }else{
                first = player;
                second= enemy;
            }

            while (player.is_alive() && enemy.is_alive()){
                first.attack(second);
                if(!second.is_alive()) break;
                second.attack(first);
            }
            System.out.println("player" +(player.is_alive() ? player.name : enemy.name) +"wins the battle");
        }catch (Exception e){
            System.out.println(" error during battle : " + e.getMessage());
        }finally{
            System.out.println("battle ended");
        }
    }
}

public class FantasyBattle {
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        try{
            System.out.println("List of players");
            System.out.println("1.Warrior");
            System.out.println("2.Mage");
            System.out.println("3.Archer");
            System.out.println("choose the player : ");

            int choice = sc.nextInt();
            Character player;

            switch (choice){
                case 1:
                    player = new Warrior("Thor");
                    break;
                case 2:
                    player = new Mage("Gandalf");
                    break;
                case 3:
                    player = new Archer("Alex");
                    break;
                default :
                    throw new InputMismatchException("invalid input");
            }

            Character enemy = new Warrior("Enemy Warrior");

            Battle.startBattle(player, enemy);
        }catch (InputMismatchException e){
            System.out.println("invalid input");
        }catch(Exception e){
            System.out.println("unexpected error : "+e.getMessage());
        }finally{
            System.out.println("game over.");
        }
    }
}
