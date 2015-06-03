/**
 * info1103 - assignment 5
 * Andrew Houghton
 * ahou8288
 */

public class Farm {

    private Crop[][] crops;
    
	private int height=0;
	private int width=0;
	private int coins;
	
	public int getCoins() {
		return coins;
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
				if (j==getWidth()-1){
					System.out.printf("%d",crops[j][i].isSprayed()?1:0);
				}else{
					System.out.printf("%d ",crops[j][i].isSprayed()?1:0);
				}
			}
			System.out.printf("\n");
		}
	}
	public void showInfected() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				
				if (j==getWidth()-1){
					System.out.printf("%d",crops[j][i].isInfested()?1:0);
				}else{
					System.out.printf("%d ",crops[j][i].isInfested()?1:0);
				}
			}
			System.out.printf("\n");
		}
	}
	public void grow() {
		for (int i=0;i<getHeight();i++){
			for (int j=0;j<getWidth();j++){
				crops[j][i].grow();
			}
		}
	}
}
