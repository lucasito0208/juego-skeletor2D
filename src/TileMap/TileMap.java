package TileMap;

import clase9menudinamico.PanelJuego;
import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;



public class TileMap {
	
	// position
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	// map
	private int[][] map;
	private int tileSize;
	private int nmapfil;
	private int nmapcol;
	private int width;
	private int height;
        
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
        int ntilefilas;
        int ntilecolumnas;
	
	// drawing
	private int rowOffset;
	private int colOffset;
	private int nfilaspintado;
	private int numcolpintado;
        
        //limites mapa total
        private int limitewidht;
        private int limiteheight;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		nfilaspintado = PanelJuego.HEIGHT / tileSize + 2;
		numcolpintado = PanelJuego.WIDTH / tileSize + 2;
		tween = 0.07;
	}
	
	public void loadTiles(String nombre) throws IOException {
		
	BufferedImage tileset=ImageIO.read(this.getClass().getResourceAsStream(nombre));
        this.ntilecolumnas=tileset.getHeight()/tileSize;
        this.ntilefilas=tileset.getWidth()/tileSize;
        tiles = new Tile[ntilefilas][ntilecolumnas];
        BufferedImage subi;
        int contador=0;
        for(int i=0;i<this.ntilefilas;i++){
            for(int j=0;j<this.ntilecolumnas;j++){
                Tile tile;
                subi=tileset.getSubimage(i*tileSize, j*tileSize, tileSize, tileSize);
                tile=new Tile(subi,0,contador);
                tile.setType(tile.asignarTipo(contador));
                tiles[i][j]=tile;  
                contador++;
            }
        }  
	}
	
	public void loadMap(String s) throws IOException {
	InputStream in = getClass().getResourceAsStream(s);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			nmapcol = Integer.parseInt(br.readLine());
			nmapfil = Integer.parseInt(br.readLine());
			map = new int[nmapfil][nmapcol];
			width = nmapcol * tileSize;
			height = nmapfil * tileSize;
			
			xmin = PanelJuego.WIDTH - width;
			xmax = 0;
			ymin = PanelJuego.HEIGHT - height;
			ymax = 0;
			
                        this.limitewidht=this.tileSize*nmapcol;
                        this.height=this.tileSize*nmapfil;
                        
			String delims = ",";
			for(int row = 0; row < nmapfil; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < nmapcol; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		
	
	public int getTipo(int row, int col) {
		int valor = map[row][col];
		int r = valor / this.ntilecolumnas;
		int c = valor % this.ntilecolumnas-1;
                if(c>=0){
                   return tiles[r][c].getType(); 
                }else{
                    return 0;
                }
		
	}
	
	public void setTween(double d) { tween = d; }
	
	public void setPosition(double x, double y) {		
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		ajustarLimites();
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	private void ajustarLimites() {
		if(x < xmin){
                    x = xmin;
                }
		if(y < ymin){
                    y = ymin;
                }
		if(x > xmax){
                    x = xmax;
                }
		if(y > ymax){
                    y = ymax;
                }
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = rowOffset;row < rowOffset + nfilaspintado;row++) {
                    if (row>=nmapfil) break;
			for(int col = colOffset;col < colOffset + numcolpintado;col++) {
                                if (col>=nmapcol) break;
				int rc = map[row][col];
				int r = rc / this.ntilefilas;
				int c = rc % this.ntilecolumnas-1;
				if(c>=0){
                                   g.drawImage(tiles[c][r].getImage(),(int)x + col * tileSize,(int)y + row * tileSize,null); 
                                }
				
				
			}
			
		}
		
	}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getNumRows() {
        return nmapfil;
    }

    public void setNumRows(int nmapfil) {
        this.nmapfil = nmapfil;
    }

    public int getNumCols() {
        return nmapcol;
    }

    public void setNumCols(int nmapcol) {
        this.nmapcol = nmapcol;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getTileset() {
        return tileset;
    }

    public void setTileset(BufferedImage tileset) {
        this.tileset = tileset;
    }

    public int getNumTilesAcross() {
        return numTilesAcross;
    }

    public void setNumTilesAcross(int numTilesAcross) {
        this.numTilesAcross = numTilesAcross;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getNtilefilas() {
        return ntilefilas;
    }

    public void setNtilefilas(int ntilefilas) {
        this.ntilefilas = ntilefilas;
    }

    public int getNtilecolumnas() {
        return ntilecolumnas;
    }

    public void setNtilecolumnas(int ntilecolumnas) {
        this.ntilecolumnas = ntilecolumnas;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(int rowOffset) {
        this.rowOffset = rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }

    public void setColOffset(int colOffset) {
        this.colOffset = colOffset;
    }

    public int getNumRowsToDraw() {
        return nfilaspintado;
    }

    public void setNumRowsToDraw(int nfilaspintado) {
        this.nfilaspintado = nfilaspintado;
    }

    public int getNumColsToDraw() {
        return numcolpintado;
    }

    public void setNumColsToDraw(int numcolpintado) {
        this.numcolpintado = numcolpintado;
    }

    public int getNmapfil() {
        return nmapfil;
    }

    public void setNmapfil(int nmapfil) {
        this.nmapfil = nmapfil;
    }

    public int getNmapcol() {
        return nmapcol;
    }

    public void setNmapcol(int nmapcol) {
        this.nmapcol = nmapcol;
    }

    public int getNfilaspintado() {
        return nfilaspintado;
    }

    public void setNfilaspintado(int nfilaspintado) {
        this.nfilaspintado = nfilaspintado;
    }

    public int getNumcolpintado() {
        return numcolpintado;
    }

    public void setNumcolpintado(int numcolpintado) {
        this.numcolpintado = numcolpintado;
    }

    public int getLimitewidht() {
        return limitewidht;
    }

    public void setLimitewidht(int limitewidht) {
        this.limitewidht = limitewidht;
    }

    public int getLimiteheight() {
        return limiteheight;
    }

    public void setLimiteheight(int limiteheight) {
        this.limiteheight = limiteheight;
    }
    
    
    
	
}



















