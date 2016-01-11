package shazbot;

import battlecode.common.Clock;
import battlecode.common.Team;

public class SoldierClass extends RobotPlayer{
	
	public static void run(){
	    while (true) {
	        try {
	        	emptyIndicatorStrings();
            	updateNearbyEnemies();
            	handleMessageQueue();
            	
            	//If there are more enemies than allies nearby, retreat to the nearest archon.
            	if(attackableTraitors.length + attackableZombies.length > veryCloseAllies.length){
            		retreatToArchon();
            	}
            	
            	//Retreat if our health is less than 50%
            	if(rc.getHealth() < myRobotType.maxHealth / 2){
            		retreatToArchon();
            		rc.setIndicatorString(2, "Retreating to archon @: " + nearestArchon.toString());
            	}
            	
            	//This is redundant checking...
            	if(attackableTraitors.length > 0){
            		attack(enemyTeam);
            		rc.setIndicatorString(0, "Attacking Traitors");
            	}else if(attackableZombies.length > 0){
            		attack(Team.ZOMBIE);
            		rc.setIndicatorString(0, "Attacking Zombies");           		
            	}else{
            		moveToClosestEnemy();
            		rc.setIndicatorString(0, "Moving");
            	}
            	
	            Clock.yield();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	    }  		
	}
}
