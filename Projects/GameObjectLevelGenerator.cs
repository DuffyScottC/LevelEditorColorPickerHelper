using UnityEngine;

public class GameObjectLevelGenerator : MonoBehaviour {

    [Tooltip("The texture that will be used to draw\n" +
             "the level.")]
    public Texture2D[] levelLayers;
    [Space(10)]
    [Tooltip("The size of a grid square in the game\n" +
             "that corospond to the pixels")]
    //end of start section
	public float gridSize = 1.0f;
	[Header("Misc Entities")]
	public GameObjectEntity[] MiscEntities = new GameObjectEntity[] {
	};
	[Tooltip("Must have the same size as MiscEntities")]
	public GameObject[] gameObjects;

	[Header("Player Entities")]
	public GameObjectEntity[] PlayerEntities = new GameObjectEntity[] {
		new ("Player 1", new Color32(0, 0, 255, 255))
	};
	[Tooltip("Must have the same size as PlayerEntities")]
	public GameObject[] gameObjects;

	[Header("Terrain Entities")]
	public GameObjectEntity[] TerrainEntities = new GameObjectEntity[] {
		new ("Barrier", new Color32(124, 107, 68, 255)),
		new ("Barrier Breakable", new Color32(216, 177, 139, 255)),
		new ("Barrier Hole", new Color32(41, 38, 36, 255)),
		new ("Ground", new Color32(228, 206, 156, 255)),
	};
	[Tooltip("Must have the same size as TerrainEntities")]
	public GameObject[] gameObjects;


		for (int i = 0; i < gameObjects.Length; i++) {
			MiscEntities.gameObject = gameObjects[i];
		}

		for (int i = 0; i < gameObjects.Length; i++) {
			PlayerEntities.gameObject = gameObjects[i];
		}

		for (int i = 0; i < gameObjects.Length; i++) {
			TerrainEntities.gameObject = gameObjects[i];
		}
        //start of middle section
        for (int i = 0; i < levelLayers.Length; i++) {
            GenerateLevel(i);
        }
    }

    private void GenerateLevel(int index) {
        //Cycle through the pixels in the level:
        //first cycle through the x pixels (columns)
        for (int x = 0; x < levelLayers[index].width; x++) {
            //for every x pixel (column) cycle through the y pixels (rows)
            for (int y = 0; y < levelLayers[index].height; y++) {
                //generate a tile using this pixel position
                GenerateTile(x, y, index);
            }
        }
    }

    /// <summary>
    /// Instantiates the entity's prefab at the given (x,y) position if the 
    /// passed in pixelColor matches the entity's color.
    /// </summary>
    /// <param name="entity">The Entity to instantiate.</param>
    /// <param name="pixelColor">The pixelColor to match with the Entity's 
    /// color.</param>
    /// <param name="x">The x coordinate of the pixel's position.</param>
    /// <param name="y">The y coordinate of the pixel's position.</param>
    private void instantiateIfMatch(GameObjectEntity entity, Color32 pixelColor, int x, int y) {
        if (entity.color.Equals(pixelColor)) {
            //convert the grid position into actuall world coordinates
            Vector2 position = new Vector2(x * gridSize, y * gridSize);
            //add the entity's offset to the position
            position += entity.offset;
            //instantiate the prefab
            Instantiate(entity.gameObject, position, Quaternion.identity, transform);
        }
    }

    /// <summary>
    /// Generates the tile using the color of the passed-in tileCoordinate
    /// </summary>
    /// <param name="x">The x coordinate.</param>
    /// <param name="y">The y coordinate.</param>
    private void GenerateTile(int x, int y, int index) {
        Color32 pixelColor = levelLayers[index].GetPixel(x, y);

        if (pixelColor.a == 0) {
            //ignore transparent pixels
            return;
        }

        //end of middle section
		foreach (GameObjectEntity entity in MiscEntities) {
			instantiateIfMatch(entity, pixelColor, x, y);
		}
		foreach (GameObjectEntity entity in PlayerEntities) {
			instantiateIfMatch(entity, pixelColor, x, y);
		}
		foreach (GameObjectEntity entity in TerrainEntities) {
			instantiateIfMatch(entity, pixelColor, x, y);
		}
	}
}

