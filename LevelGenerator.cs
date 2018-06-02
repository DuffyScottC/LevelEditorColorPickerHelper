using UnityEngine;

public class LevelGenerator : MonoBehaviour {

     [Tooltip("The texture that will be used to draw\n" +
              "the level.")]
    public Texture2D level;

     [Tooltip("The size of a grid square in the game\n" +
              "that corospond to the pixels")]
     public float gridSize = 32f;
     //end of start section
	//Misc
#if UNITY_EDITOR
	[EntityArrayAttribute(new string[] {
		"Entity 1",
		"Entity 3",
		"Entity 4",
		"Entity 7",
		"Entity 9",
		"Entity 11",
		"Entity 12",
		"Entity 13"
	})]
#endif
	public Entity[] MiscEntities = new Entity[] {
		new Entity(new Color32(0, 0, 1, 255)),
		new Entity(new Color32(0, 190, 3, 255)),
		new Entity(new Color32(0, 0, 217, 255)),
		new Entity(new Color32(0, 195, 86, 255)),
		new Entity(new Color32(255, 210, 9, 255)),
		new Entity(new Color32(31, 255, 255, 255)),
		new Entity(new Color32(255, 68, 255, 255)),
		new Entity(new Color32(255, 137, 180, 255))
	};

	//Terrain
#if UNITY_EDITOR
	[EntityArrayAttribute(new string[] {
		"Ground",
		"Entity 2",
		"Entity 6",
		"Entity 10",
	})]
#endif
	public Entity[] TerrainEntities = new Entity[] {
		new Entity(new Color32(0, 0, 0, 255)),
		new Entity(new Color32(179, 0, 2, 255)),
		new Entity(new Color32(101, 48, 6, 255)),
		new Entity(new Color32(108, 209, 10, 255)),
	};

	//Enemies
#if UNITY_EDITOR
	[EntityArrayAttribute(new string[] {
		"Dummy",
		"Entity 5",
		"Entity 8",
	})]
#endif
	public Entity[] EnemiesEntities = new Entity[] {
		new Entity(new Color32(255, 0, 0, 255)),
		new Entity(new Color32(185, 255, 5, 255)),
		new Entity(new Color32(0, 0, 8, 255)),
	};

    //start of middle section
    public void Start() {
          //run the GenerateLevel code
          GenerateLevel();
    }

    private void GenerateLevel () {
          //Cycle through the pixels in the level:
          //first cycle through the x pixels (columns)
          for (int x = 0; x < level.width; x++) {
               //for every x pixel (column) cycle through the y pixels (rows)
            for (int y = 0; y < level.height; y++) {
                    //generate a tile using this pixel position
                GenerateTile(x, y);
            }
        }
    }

    /// <summary>
    /// Generates the tile using the color of the passed-in tileCoordinate
    /// </summary>
    /// <param name="x">The x coordinate.</param>
    /// <param name="y">The y coordinate.</param>
    private void GenerateTile (int x, int y) {
        Color pixelColor = level.GetPixel(x, y);      

        if (pixelColor.a == 0) {
            // The pixel is transparrent. Let's ignore it!
            return;
        }
        //end of middle section
		foreach (Entity entity in MiscEntities) {
			if (entity.color.Equals(pixelColor)) {
				Vector2 position = new Vector2(x*gridSize, y*gridSize);
				Instantiate(entity.prefab, position, Quaternion.identity, transform);
			}
		}
		foreach (Entity entity in TerrainEntities) {
			if (entity.color.Equals(pixelColor)) {
				Vector2 position = new Vector2(x*gridSize, y*gridSize);
				Instantiate(entity.prefab, position, Quaternion.identity, transform);
			}
		}
		foreach (Entity entity in EnemiesEntities) {
			if (entity.color.Equals(pixelColor)) {
				Vector2 position = new Vector2(x*gridSize, y*gridSize);
				Instantiate(entity.prefab, position, Quaternion.identity, transform);
			}
		}
	}
}

