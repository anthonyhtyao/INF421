
public class StableMatching implements StableMatchingInterface{
	public boolean prefer(int[] prefLst,int fst,int scd)
	{
		if(prefLst[fst]<prefLst[scd])
			return true;
		else
			return false;
	}
	public int[] constructStableMatching(int n,int[][] menPrefs,int[][] womenPrefs) {
		int[][] womenPrefIndex = new int[n][n];	
		int[] bride = new int[n];
		int[] womenPair = new int[n];	
		int[] asked = new int[n];
		int[] notEngaged = new int[n];	//pile of g not engaged
		int indNot = 0;
		for(int i=0;i<n;i++)
		{
			bride[i]=-1;
			notEngaged[i] = i;
			womenPair[i]=-1;
			asked[i]=-1;
			for(int j=0;j<n;j++)
			{
				int gPref = womenPrefs[i][j];
				womenPrefIndex[i][gPref] = j;
			}
		}
		int g;
		while(true)
		{
			if(indNot == n)
				break;
			g = notEngaged[indNot];
			int indAsked = asked[g]; 
			if(indAsked == n-1)
				break; //g has asked all women
			int f = menPrefs[g][indAsked+1];
			asked[g]++;
			int g1 = womenPair[f];
			if(g1 == -1)//f is not engaged
			{
				bride[g] = f; 
				womenPair[f] = g;
				indNot++;
			}
			else
			{
				if(prefer(womenPrefIndex[f],g,g1)) //f is with g1
				{
					bride[g1] = -1;
					bride[g] = f;
					womenPair[f] = g;
					notEngaged[indNot] = g1;
				}
			}
		}
		return bride;
	}
}
