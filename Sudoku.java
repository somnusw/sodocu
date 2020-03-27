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
	
	//�����ļ�������
	public static String inputFilename;
	//�����ļ�������
	public static String outputFilename;
	//������
	public static int m;
	//�����ļ��еľ������
	public static int n;
	//shuDu[][]��������Ķ�ά����
    public static int shuDu[][] = new int[9][9];
	
	
	/**�����������ȡ�����ĺ���*/
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
	
	/**��input�ļ��о������ShuDu[][]��*/
	public static void file_arryput_into_ShuDu(int[][] shuDu) {
        Sudoku.shuDu = shuDu;
    }
	
	/**������Ϊ���λ���������֣���д��output�ļ���*/
	public static void inside(int ini,int m)
	{
		//һ��һ���Ľ��ж�ȡ
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
		
		//����һ�������д��output�ļ���
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
				//�ر��ļ�
	            fw.close();
			}catch (Exception e) {  
                e.printStackTrace();  
            }  
			return;
		}
	}
	
	/**��inside����ȷ��ÿ��Ϊ0�ĸ�������֮���жϸø�������Ӧ���к����Ƿ����ظ������֣�����false*/
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
			 //(divideX,divideY)��(x,y)����С4��������Ͻǵ�����
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
			 //(divideX,divideY)��(x,y)����С4��������Ͻǵ�����
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
			 //(divideX,divideY)��(x,y)����С4��������Ͻǵ�����
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
			 //(divideX,divideY)��(x,y)����С4��������Ͻǵ�����
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
		//���ļ�����ȡ
		Reader reader = new InputStreamReader(new FileInputStream(myFile),"UTF-8"); 
		
		int accept_file_array;
		//��0��ʼ������
		int i=0;
		//��0��ʼ������
		int j=0;
		//ֱ������ĩβ��reader.read()����ĩβ����-1��
		while ((accept_file_array=reader.read())!=-1)
		{
			 //һ��һ���������������
			if ( (((char) accept_file_array) != '\n') &&(((char) accept_file_array) != ' '))
			{
				if(i<m)
				{
					//���н��б���
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
		        		//���н��б���	
		        		i++;
		        		j=0;
		        		generateShuDu[i][j]=((char)  accept_file_array)-48;
		        	}
				}
				//��������һ������֮��
		        if(i==m){
		        	if(n!=0){
		        		//��input�ļ��е������������shuDu[][]��	
		        		file_arryput_into_ShuDu(generateShuDu);
			          //���û��ݷ����õ�ÿһ��������Ϊ0��λ�ø��������ֵ
		        		inside(0,m);
			            n--;
			          //ÿ����һ������ͽ��к��У�i��j�����㣬ͬʱ��������1
			            i=0;j=0;
		        	}
			    }
		    }
	     }
		reader.close();
	}
}