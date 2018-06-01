using UnityEngine;

public class LevelGenerator : MonoBehaviour {

    public Texture2D map;
 
	public ColorToPrefab[] colorMappings = new ColorToPrefab[] {
		new ColorToPrefab(new Color(0f, 0f, 0f, 100f))
    };

    public void Start() {
		GenerateLevel();
    }

    private void GenerateLevel () {
        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                GenerateTile(x, y);
            }
        }
    }

    private void GenerateTile (int x, int y) {
        Color pixelColor = map.GetPixel(x, y);

        if (pixelColor.a == 0) {
            // The pixel is transparrent. Let's ignore it!
            return;
        }

        foreach (ColorToPrefab colorMapping in colorMappings) {
            if (colorMapping.color.Equals(pixelColor)) {
                Vector2 position = new Vector2(x, y);
                Instantiate(colorMapping.prefab, position, Quaternion.identity, transform);
            }
        }
    }
}