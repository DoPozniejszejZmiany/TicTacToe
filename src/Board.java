import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private int[][] brd = new int [][]{
            {0, 0 ,0},
            {0, 0 ,0},
            {0, 0 ,0}
    };
    private int[][] pop = new int [][]{
            {0, 0 ,0},
            {0, 0 ,0},
            {0, 0 ,0}
    };

    public boolean set(int znak, int x, int y) {
        if(brd[x][y]==0) {
            brd[x][y] = znak;
            return true;
        }
        return false;
    }
    public int scanwin(){
        int i;
        for(i=0; i<3; i++){
            if(brd[0][i]==brd[1][i]&&brd[2][i]==brd[1][i]&&brd[0][i]!=0) return brd[0][i];
        }
        for(i=0; i<3; i++){
            if(brd[i][0]==brd[i][1]&&brd[i][2]==brd[i][1]&&brd[i][0]!=0) return brd[i][0];
        }
        if(brd[0][0]==brd[1][1]&&brd[2][2]==brd[1][1]&&brd[0][0]!=0) return brd[0][0];
        if(brd[0][2]==brd[1][1]&&brd[2][0]==brd[1][1]&&brd[0][2]!=0) return brd[0][0];
        return 0;
    }
    private void scanpop(){
        int j,k,i;
        int[] p = new int[3];
        for(k=0;k<3;k++){
            for(j=0;j<3;j++){
                if(brd[j][k]==0){
                    p[0] = brd[j][(k+1)%3]*brd[j][(k+2)%3];
                    p[1] = brd[(j+1)%3][k]*brd[(j+2)%3][k];
                    if((j==0||j==2)&&(k==0||k==2)) {
                        p[2] = brd[1][1] * brd[(j + 2) % 4][(k + 2) % 4];
                    } else p[2]=0; //corners
                    for(i=0; i<3; i++)
                    switch(p[i]){
                        case 1 : {
                            p[i]=8;
                            break;
                        }
                        case 4 : {
                            p[i]=6;
                            break;
                        }
                        default: break;
                    }
                    pop[j][k] = p[0]+p[1]+p[2];
                    if(j==k && k==1) pop[j][k]=pop[j][k]+3; //center
                }
                else pop[j][k] = -1;
            }
        }
    }
    public boolean scanbrd (){
        int j,k,i=0;
        for(k=0;k<3;k++){
            for(j=0;j<3;j++){
                if(brd[j][k]!=0) i++;
            }
        }
        if(i==9) return true;
        else return false;
    }
    private void choosesq(){
        int maxpop = -1;
        int j,k,x=0,y=0;
        Random r = new Random();
        for(k=0;k<3;k++){
            for(j=0;j<3;j++){
                if(pop[j][k]>maxpop){
                    x=j;
                    y=k;
                    maxpop=pop[j][k];
                }
                if(pop[j][k]==maxpop && r.nextBoolean()){
                    x=j;
                    y=k;
                }
            }
        }
        if(!set(1, x, y)) System.out.println("!!!ILLEGAL MOVE!!!");
    }
    public void aiMove(){
        scanpop();
        choosesq();
    }
    public boolean playerMove(){
        Scanner reader = new Scanner(System.in);
        System.out.print("Please choose X: ");
        int x=reader.nextInt()-1;
        System.out.print("Please choose Y: ");
        int y=reader.nextInt()-1;
        if(x>(-1) && x<3 && y>(-1) && y<3){
            if(set(2, x, y)) return true;
            else{
                System.out.println("This position is already taken.");
                return false;
            }
        }
        else{
            System.out.println("Please draw within the board.");
            return false;
        }
    }
    public void printBrd(){
        char fld = ' ';
        int i,j;
        for(j=0; j<3; j++){
            for(i=0; i<3; i++){
                switch(brd[i][j]){
                    case 0 :{
                        fld='.';
                        System.out.print(fld + " ");
                        break;
                    }
                    case 1 :{
                        fld='O';
                        System.out.print(fld + " ");
                        break;
                    }
                    case 2 :{
                        fld='X';
                        System.out.print(fld + " ");
                        break;
                    }
                }
            }
            System.out.println();
        }

    }
}
