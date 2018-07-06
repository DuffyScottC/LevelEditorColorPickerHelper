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
    /// Instantiates the entity's prefab at the given (x,y) position if the 
    /// passed in pixelColor matches the entity's color.
    /// </summary>
    /// <param name="entity">The Entity to instantiate.</param>
    /// <param name="pixelColor">The pixelColor to match with the Entity's 
    /// color.</param>
    /// <param name="x">The x coordinate of the pixel's position.</param>
    /// <param name="y">The y coordinate of the pixel's position.</param>
    private void instantiateIfMatch(Entity entity, Color pixelColor, int x, int y) {
    if (entity.color.Equals(pixelColor)) {
            //convert the grid position into actuall world coordinates
            Vector2 position = new Vector2(x * gridSize, y * gridSize);
            //add the entity's offset to the position
            position += entity.offset;
            //instantiate the prefab
            Instantiate(entity.prefab, position, Quaternion.identity, transform);
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
            //ignore transparent pixels
            return;
        }
        if(pixelColor.r > 0) {
            Debug.Log(pixelColor);
        }
        //end of middle section