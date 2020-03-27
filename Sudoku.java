package a;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * 
 * @author wei
 */
public class Sudoku {
	
	//输入文件的名字
	public static String inputFilename;
	//输入文件的名字
	public static String outputFilename;
	//几宫格
	public static int m;
	//输入文件中的矩阵个数
	public static int n;
	//shuDu[][]存放数独的二维矩阵
    public static int shuDu[][] = new int[9][9];
	
	
	/**输入参数并获取参数的函数*/
	public static void inputArgs(String args[]) {
		if(args.length>0&&args!=null) 
		{
			for(int i=0;i<args.length;i++)
			{
				switch(args[i])
				{
				case"-i":
					inputFilename=args[++i];
					break;
				case"-o":
					outputFilename=args[++i];
					break;
				case"-m":
					m=Integer.valueOf(args[++i]);
					break;
				case"-n":
					n=Integer.valueOf(args[++i]);
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**将input文件中矩阵放入ShuDu[][]中*/
	public static void file_arryput_into_ShuDu(int[][] shuDu) {
        Sudoku.shuDu = shuDu;
    }
	
	/**将数独为零的位置填入数字，并写进output文件中*/
	public static void inside(int ini,int m)
	{
		//一个一个的进行读取
		int x=ini/m;
		int y=ini%m;
		if(shuDu[x][y]==0) 
		{
			for(int i=1;i<=m;i++) 
			{
				shuDu[x][y]=i;
				if(outside(shuDu,x, y, i,m))
				{
					inside(ini + 1,m);
				}
				shuDu[x][y] = 0;
			}
		}else {
			inside(ini + 1,m);
		}
		
		//读完一个矩阵后写入output文件中
		if(ini==m*m)
		{
			String src="E:\\360MoveData\\Users\\BoHang Wei\\Desktop\\output2.txt";
			try
			{
				FileWriter fw = new FileWriter(src,true);
				for(int i=0;i<m;i++)
				{
					for(int j=0;j<m;j++) 
					{
						fw.write(shuDu[i][j]+" ");
					}
					 fw.write("\r\n");
				}
				fw.write("\r\n");
				//关闭文件
	            fw.close();
			}catch (Exception e) {  
                e.printStackTrace();  
            }  
			return;
		}
	}
	
	/**当inside函数确定每个为0的格子数字之后判断该格子所对应的行和列是否有重复的数字，返回false*/
	public static Boolean outside(int a[][],int x, int y, int value,int m)
	{
		for(int i=0;i<m;i++) 
		{
			if(i!=x&&a[i][y]==value)
			{
				return false;
			}
			if(i!=y&&a[x][i]==value)
			{
				return false;
			}	
		}
		
		if(m==4)
		{
			 //(divideX,divideY)是(x,y)所属小4宫格的左上角的坐标
			int divideX=x/2*2;
			int divideY=y/2*2;
			for (int i=divideX;i<divideX+2;i++)
			{
				for (int j=divideY;j<divideY+2;j++)
				{
					if (i != x && j != y && a[i][j] == value)
					{
                        return false;
                    }
				}
			}	
		}
		
		if(m==6)
		{
			 //(divideX,divideY)是(x,y)所属小4宫格的左上角的坐标
			int divideX=x/2*2;
			int divideY=y/3*3;
			for (int i=divideX;i<divideX+2;i++)
			{
				for (int j=divideY;j<divideY+3;j++)
				{
					if (i != x && j != y && a[i][j] == value)
					{
                        return false;
                    }
				}
			}	
		}
		
		if(m==8)
		{
			 //(divideX,divideY)是(x,y)所属小4宫格的左上角的坐标
			int divideX=x / 4 * 4;
			int divideY=y / 2 * 2;
			for (int i=divideX;i<divideX+4;i++)
			{
				for (int j=divideY;j<divideY+2;j++)
				{
					if (i != x && j != y && a[i][j] == value)
					{
                        return false;
                    }
				}
			}	
		}
		
		if(m==9)
		{
			 //(divideX,divideY)是(x,y)所属小4宫格的左上角的坐标
			int divideX=x / 3 * 3;
			int divideY=y / 3 * 3;
			for (int i=divideX;i<divideX+3;i++)
			{
				for (int j=divideY;j<divideY+3;j++)
				{
					if (i != x && j != y && a[i][j] == value)
					{
                        return false;
                    }
				}
			}	
		}
		
		return true;
	}
	
	
	
	public static void main (String [] args) throws IOException
	{
		inputArgs(args);
		int generateShuDu[][]=new int[10][10];   
		File myFile = new File("E:\\360MoveData\\Users\\BoHang Wei\\Desktop\\input2.txt");
		//打开文件并读取
		Reader reader = new InputStreamReader(new FileInputStream(myFile),"UTF-8"); 
		
		int accept_file_array;
		//从0开始的行数
		int i=0;
		//从0开始的列数
		int j=0;
		//直到读到末尾（reader.read()读到末尾返回-1）
		while ((accept_file_array=reader.read())!=-1)
		{
			 //一个一个矩阵的输入和输出
			if ( (((char) accept_file_array) != '\n') &&(((char) accept_file_array) != ' '))
			{
				if(i<m)
				{
					//对列进行遍历
		        	if(j<m)
		        	{
		        		if(accept_file_array!=13) 
		        		{
		        			//if(i==3&&j==0) {
	        				//System.out.println((char) accept_file_array);
	        			//}
	        			generateShuDu[i][j]=((char) accept_file_array)-48;
	        			//System.out.println(j);
		        		j++;
		        		}
		        	}else {
		        		//System.out.println(i);
		        		//对行进行遍历	
		        		i++;
		        		j=0;
		        		generateShuDu[i][j]=((char)  accept_file_array)-48;
		        	}
				}
				//当遍历完一个矩阵之后
		        if(i==m){
		        	if(n!=0){
		        		//将input文件中的盘面读入数组shuDu[][]中	
		        		file_arryput_into_ShuDu(generateShuDu);
			          //运用回溯方法得到每一个矩阵中为0的位置该填入的数值
		        		inside(0,m);
			            n--;
			          //每读完一个矩阵就将行和列（i、j）归零，同时矩阵数减1
			            i=0;j=0;
		        	}
			    }
		    }
	     }
		reader.close();
	}
}