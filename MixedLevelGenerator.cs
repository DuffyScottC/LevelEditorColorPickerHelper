using System;
using UnityEngine;
using UnityEngine.Tilemaps;
/// <summary>
/// Used for when there are Tiles and Prefabs in the same image.
/// </summary>
public class MixedLevelGenerator : MonoBehaviour {

    private GameObject grid;
    private Tilemap[] tilemapComponents;

    [Tooltip("The textures that will be used to draw\n" +
             "the level's layers. Each texture is a\n" +
             "layer of the level.")]
    public Texture2D[] levelLayers;
    [Space(10)]
    //end of start section
	public float gridSize = 1.0f;
	public Vector3 cellSize = new Vector3(1.0f, 1.0f, 0.0f);
	public Vector3 cellGap = new Vector3(0.0f, 0.0f, 0.0f);

	[Header("Misc Entities")]
	public GOEntity[] miscEntities = new GOEntity[] {
		new GOEntity("Entity 1", new Color32(255, 0, 0, 255)),
	};
	[Tooltip("Must have the same size as miscEntities")]
	public GameObject[] miscGameObjects;


	[Header("Enemy Entities")]
	public GOEntity[] enemyEntities = new GOEntity[] {
		new GOEntity("Entity 3", new Color32(255, 0, 255, 255)),
	};
	[Tooltip("Must have the same size as enemyEntities")]
	public GameObject[] enemyGameObjects;


	[Header("Misc Entities")]
	public TEntity[] miscEntities = new TEntity[] {
		new TEntity("Entity 2", new Color32(1, 255, 0, 255)),
	};
	[Tooltip("Must have the same size as miscEntities")]
	public TileBase[] miscTileBases;


	[Header("Enemy Entities")]
	public TEntity[] enemyEntities = new TEntity[] {
		new TEntity("Entity 4", new Color32(3, 255, 255, 255))
	};
	[Tooltip("Must have the same size as enemyEntities")]
	public TileBase[] enemyTileBases;

	public void Start() {
		for (int i = 0; i < miscGameObjects.Length; i++) {
			miscEntities[i].gameObject = miscGameObjects[i];
		}

		for (int i = 0; i < enemyGameObjects.Length; i++) {
			enemyEntities[i].gameObject = enemyGameObjects[i];
		}

		for (int i = 0; i < miscTileBases.Length; i++) {
			miscEntities[i].tile = miscTileBases[i];
		}

		for (int i = 0; i < enemyTileBases.Length; i++) {
			enemyEntities[i].tile = enemyTileBases[i];
		}
        //start of middle section
        //Create the grid for the tilemap layers
        grid = new GameObject("Grid");
        grid.AddComponent<Grid>();
        Grid gridComponent = grid.GetComponent<Grid>();
        gridComponent.cellSize = cellSize;
        gridComponent.cellGap = cellGap;

        //Initialize these arrays to the same size as the number of levels:
        tilemapComponents = new Tilemap[levelLayers.Length];

        //loop through all the layers in the Tilemap section of
        for (int i = 0; i < levelLayers.Length; i++) {
            GenerateLevelLayer(i);
        }

        //if this grid has no tilemap objects as children
        if (grid.transform.childCount == 0) {
            //then it is empty and we can destroy it
            Destroy(grid);
        }
    }

    /// <summary>
    /// Generates a layer of the level using the Texture2D layer in
    /// levelLayers and, possibly, the tileMapComponent if this
    /// layer has any Entities of class TILE
    /// </summary>
    /// <param name="index">The index of the given layer.</param>
    private void GenerateLevelLayer(int index) {
        //Cycle through the pixels in the level:
        //first cycle through the x pixels (columns)
        for (int x = 0; x < levelLayers[index].width; x++) {
            //for every x pixel (column) cycle through the y pixels (rows)
            for (int y = 0; y < levelLayers[index].height; y++) {
                //generate a Tile/GameObject using this pixel position
                GenerateSquare(x, y, index);
            }
        }
    }

    /// <summary>
    /// Places the <paramref name="entity"/>'s tile at the given
    /// (x,y) position if the passed in pixelColor matches the
    /// <paramref name="entity"/>'s color.
    /// </summary>
    /// <param name="entity">The Entity to instantiate.</param>
    /// <param name="pixelColor">The pixelColor to compare to the Entity's 
    /// color.</param>
    /// <param name="x">The x coordinate of the pixel's position.</param>
    /// <param name="y">The y coordinate of the pixel's position.</param>
    /// <param name="index">The index of the current layer.</param>
    private void placeTileIfColorMatches(TEntity entity, Color32 pixelColor, int x, int y, int index) {
        //if the Entity's color matches the pixelColor
        if (entity.color.Equals(pixelColor)) {
            //if there is not a layer for this index yet
            if (tilemapComponents[index] == null) {
                //create a new layer, because we are about to add a Tile
                tilemapComponents[index] = CreateTilemapGameObjectLayer("Layer " + (index + 1));
            }
            //convert the pixel position into a tilemap position
            Vector3Int pixelPos = new Vector3Int(x, y, 0);
            //place the tileEntity's tile in the Tilemap
            tilemapComponents[index].SetTile(pixelPos, entity.tile);
        }
    }

    /// <summary>
    /// Places the <paramref name="entity"/>'s gameObject at the given
    /// (x,y) position if the passed in pixelColor matches the
    /// <paramref name="entity"/>'s color.
    /// </summary>
    /// <param name="entity">The Entity to instantiate.</param>
    /// <param name="pixelColor">The pixelColor to compare to the Entity's 
    /// color.</param>
    /// <param name="x">The x coordinate of the pixel's position.</param>
    /// <param name="y">The y coordinate of the pixel's position.</param>
    /// <param name="index">The index of the current layer.</param>
    private void placeGameObjectIfColorMatches(GOEntity entity, Color32 pixelColor, int x, int y, int index) {
        //if the Entity's color matches the pixelColor
        if (entity.color.Equals(pixelColor)) {
            //convert the grid position into actuall world coordinates
            Vector2 pos = new Vector2(x * gridSize, y * gridSize);
            //add the entity's offset to the position
            pos += entity.offset;
            //instantiate the GameObject
            Instantiate(entity.gameObject, pos, Quaternion.identity, transform);
        }
    }

    /// <summary>
    /// Creates a GameObject with a Tilemap component and a TilemapRenderer 
    /// component and returns it.
    /// </summary>
    /// <returns>The tilemap game object layer.</returns>
    /// <param name="layerName">The name of the layer.</param>
    private Tilemap CreateTilemapGameObjectLayer(String layerName) {
        //create a gameobject to be a tilemap layer
        GameObject tilemapLayer = new GameObject(layerName);
        tilemapLayer.AddComponent<Tilemap>();
        tilemapLayer.AddComponent<TilemapRenderer>();
        //This is where you might add a TilemapCollider2D component
        tilemapLayer.transform.SetParent(grid.transform);
        //return the new tilemap
        return tilemapLayer.GetComponent<Tilemap>();
    }

    /// <summary>
    /// Generates a Tile/GameObject using the color of the passed-in coordinate
    /// </summary>
    /// <param name="x">The x coordinate.</param>
    /// <param name="y">The y coordinate.</param>
    /// <param name="index">The index of the current layer</param>
    private void GenerateSquare(int x, int y, int index) {
        Color32 pixelColor = levelLayers[index].GetPixel(x, y);

        if (pixelColor.a == 0) {
            //ignore transparent pixels
            return;
        }

        //Cycle through the TEntities and the GOEntities to see which
        //entity matches this pixelColor
        //end of middle section
        
		foreach (GOEntity entity in miscEntities) {
			placeGameObjectIfColorMatches(entity, pixelColor, x, y, index);
		}
		foreach (GOEntity entity in enemyEntities) {
			placeGameObjectIfColorMatches(entity, pixelColor, x, y, index);
		}
		foreach (TEntity entity in miscEntities) {
			placeTileIfColorMatches(entity, pixelColor, x, y, index);
		}
		foreach (TEntity entity in enemyEntities) {
			placeTileIfColorMatches(entity, pixelColor, x, y, index);
		}
	}
}

