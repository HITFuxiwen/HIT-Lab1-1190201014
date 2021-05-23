package P1;

import java.io.*;
public class magic {
	public static boolean f=true;
	public static boolean generateMagicSquare(int n)
	{
		if(n<0 || n%2==0) 
		{
			System.out.println(false);
			return false;	
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;//一共n*n个数
		for(i = 1; i <= square; i++) 
		{
			magic[row][col] = i;//row 行 col 列
			if(i % n == 0)row++;
			else 
			{
				if(row == 0) row = n -1;
				else row--;
				if(col == (n-1)) col = 0;
				else col++;
			}
		}
		//row 从0开始减，对n取模，每n次加一
		//col 从n/2开始加对n取模
		for (i = 0; i < n; i++) 
		{
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		return true;
	}

	public static boolean isLegalMagicSquare(String fileName)
	{
		int[][] a=new int[1000][1000];
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str; 
			int t=0;
			int p=-1;
			while((str=in.readLine())!=null)
			{
				if(str.contains(".")|| str.contains(" "))
				{
					return f=false;
				}
				t++;
				String[] s=str.split("\t");
				if(p==-1) p=s.length;
				else 
				{
					if(p!=s.length)
					{
						return f=false;
					}
				}
				for(int i=0;i<s.length;i++)
				{
					a[t][i+1]=Integer.valueOf(s[i]);
					if(a[t][i+1]<=0)
					{
						return f=false;
					}
					//System.out.print(a[t][i+1]);
					//System.out.print(' ');
				}
				//System.out.println();
			}
			if(t!=p)
			{
				return f=false;
			}
			int ans=0,tmp=0;
			for(int i=1;i<=t;i++) ans+=a[1][i];
			for(int i=1;i<=t;i++)
			{
				tmp=0;
				for(int j=1;j<=t;j++)
				{
					tmp+=a[i][j];
				}
				if(tmp!=ans) return false;
			}
			tmp=0;
			for(int i=1;i<=t;i++) tmp+=a[i][i];
			if(tmp!=ans) return false;
			tmp=0;
			for(int i=1;i<=t;i++) tmp+=a[i][t-i+1];
			if(tmp!=ans) return false;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return true;
	}
	public static void main(String[] args)
	{
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/1.txt"));
		if(f==false) System.out.println("输入有误");
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/2.txt"));
		if(f==false) System.out.println("输入有误");
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/3.txt"));
		if(f==false) System.out.println("输入有误");
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/4.txt"));
		if(f==false) System.out.println("输入有误");
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/5.txt"));
		if(f==false) System.out.println("输入有误");
		f=true;
		System.out.println(isLegalMagicSquare("./src/p1/6.txt"));
		if(f==false) System.out.println("输入有误");
		//generateMagicSquare(5);
	}
}
