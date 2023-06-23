package pt.iscte.dcti.poo.sokoban;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import pt.iscte.dcti.poo.sokoban.interfaces.ActiveObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class AssetHelper {
	private AssetHelper() {
		throw new IllegalStateException("AssetHelper is a Utility class, should not be instantiated");
	}
	
	public static List<ImageTile> getObjectsInPosition(List<ImageTile> list, Point2D point2D) {
		return list.stream().filter(point -> point2D.equals(point.getPosition())).collect(Collectors.toList());
	}
	
	public static ImageTile getTileFromPosition(List<ImageTile> list, Point2D point2D) {
	    return list.stream().filter(point -> point2D.equals(point.getPosition())).findFirst().orElse(null);
	}
	
	public static List<ImageTile> getTiles(List<ImageTile> list, Point2D point2D) {
	    return list.stream().filter(point -> point2D.equals(point.getPosition())).collect(Collectors.toList());
	}
	
	public static boolean containsActiveObjects(List<ImageTile> tiles) {
		for (ImageTile tile : tiles) {
	    	if (tile instanceof ActiveObject) return true;
	    }
		
		return false;
	}
	
	public static List<ImageTile> getActiveObjects(List<ImageTile> tiles) {
		return tiles.stream().filter(tile -> tile instanceof ActiveObject).collect(Collectors.toList());
	}
	
	public static <T> boolean isLastTile(Class<T> tClass, List<ImageTile> tiles) {
		int count = 0;
		
	    for (ImageTile tile : tiles) {
	    	if (tClass.isAssignableFrom(tile.getClass())) count++;
	    }
	    
	    return count <= 1;
	}
	
	public static <T> void removeTile(Class<T> tClass, List<ImageTile> tiles, Point2D position) {
		Iterator<ImageTile> i = tiles.iterator();
		while (i.hasNext()) {
			ImageTile tile = i.next();
			
			if (tClass.isAssignableFrom(tile.getClass()) && tile.getPosition().equals(position)) {
				ImageMatrixGUI.getInstance().removeImage(tile);
				i.remove();
			}
		}		
	}
	
	public static void replaceAsset(List<ImageTile> tiles, Point2D point2D, boolean removeAfter) {
		ImageTile target = AssetHelper.getTileFromPosition(tiles, point2D);
		
		if (removeAfter) ImageMatrixGUI.getInstance().removeImage(target);
		
		tiles.remove(target);
		
		ImageTile floor = Asset.getTile(Asset.FLOORTILE, point2D);
		tiles.add(floor);
		
		ImageMatrixGUI.getInstance().addImage(floor);
	}
	
	public static void replaceAsset(List<ImageTile> tiles, Point2D point2D, Asset asset) {
		ImageTile target = AssetHelper.getTileFromPosition(tiles, point2D);
		
		ImageMatrixGUI.getInstance().removeImage(target);
		
		tiles.remove(target);
				
		ImageTile floor = Asset.getTile(asset, point2D);
		
		tiles.add(floor);
		ImageMatrixGUI.getInstance().addImage(floor);
	}
}
