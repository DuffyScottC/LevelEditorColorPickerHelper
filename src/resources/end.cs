    //start of end section
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
          Debug.Log(pixelColor);
          foreach (Entity entity in entities) {
            if (entity.color.Equals(pixelColor)) {
                    Vector2 position = new Vector2(x*gridSize, y*gridSize);
                Instantiate(entity.prefab, position, Quaternion.identity, transform);
            }
        }
    }
}