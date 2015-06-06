/**
 * info1103 - assignment 5
 * Andrew Houghton
 * ahou8288
 */

public class Farm {

    public Crop[][] crops;
    
	private int height=0;
	private int width=0;
	private int coins;
	
	public int getCoins() {
		return coins;
	}
	
	public boolean payCoins(int payment){
	    if (payment<0){
    		System.out.printf("Collect %d coins\n",-payment);
			coins-=payment;
			return true;
	    } else {
    		if (coins>=payment){
    			coins-=payment;
    			System.out.printf("Cost %d coins\n",payment);
    			return true;
    		} else {
    			return false;
    		}
	    }
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
    
    public Farm(int height,int width) {
    	coins=100;
    	crops=new Crop[width][height];
    	
    	this.height=height;
    	this.width=width;
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                crops[j][i]=new Crop();
            }
        }
    }
	public void showWater() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				if (j==getWidth()-1){
					System.out.printf("%d",crops[j][i].getWater());
				}else{
					System.out.printf("%d ",crops[j][i].getWater());
				}
			}
			System.out.printf("\n");
		}
	}
	public void showHeight() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				if (j==getWidth()-1){
					System.out.printf("%d",crops[j][i].getHeight());
				}else{
					System.out.printf("%d ",crops[j][i].getHeight());
				}
			}
			System.out.printf("\n");
		}
	}
	public void showSprayed() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
			    if (crops[j][i].isSprayed()){
    				if (j==getWidth()-1){
    					System.out.printf("%s","+");
    				}else{
    					System.out.printf("%s ","+");
    				}
			    } else{
			        if (j==getWidth()-1){
    					System.out.printf("%s",".");
    				}else{
    					System.out.printf("%s ",".");
    				}
			    }
			}
			System.out.printf("\n");
		}
	}
	public void showInfected() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				if (crops[j][i].isInfested()){
    				if (j==getWidth()-1){
    					System.out.printf("%s","x");
    				}else{
    					System.out.printf("%s ","x");
    				}
			    } else{
			        if (j==getWidth()-1){
    					System.out.printf("%s",".");
    				}else{
    					System.out.printf("%s ",".");
    				}
			    }
			}
			System.out.printf("\n");
		}
	}
	public void grow() {
	    //Create an identical farm
	    Farm newFarm=new Farm(getHeight(),getWidth());
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				crops[j][i].clone(newFarm,j,i);
			}
		}
		//Look at the copy to determine the new state
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				crops[j][i].grow(newFarm,j,i);
			}
		}
	}

	public void waterOne(int x1, int y1) {
		crops[y1-1][x1-1].setWater(8);
	}
	
	public void sprayOne(int x1,int y1){
	    crops[y1-1][x1-1].setSprayed(true);
	}
	
	public void infestOne(int x1,int y1){
	    crops[y1-1][x1-1].setInfested(true);
	}
	
	public void harvestOne(int x1,int y1){
	    crops[y1-1][x1-1].setHeight(0);
	}
}
