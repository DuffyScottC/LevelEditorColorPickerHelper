//start of middle section
    public void Start() {
        grid = new GameObject("Grid");
        grid.AddComponent<Grid>();
        Grid gridComponent = grid.GetComponent<Grid>();
        gridComponent.cellSize = cellSize;
        gridComponent.cellGap = cellGap;

        //initialize this array to the same size as there are levels
        tilemapComponents = new Tilemap[levelLayers.Length];
        //make a tilemap object for each layer
        for (int i = 0; i < levelLayers.Length; i++) {
            //create a gameobject to be a tilemap layer
            GameObject tilemapLayer = new GameObject("Layer " + (i+1));
            tilemapLayer.AddComponent<Tilemap>();
            tilemapLayer.AddComponent<TilemapRenderer>();
            tilemapLayer.transform.SetParent(grid.transform);
            //add the new tilemap to the tilemapComponents array
            tilemapComponents[i] = tilemapLayer.GetComponent<Tilemap>();
        }

        //loop through all the layers in the level
        for (int i = 0; i < levelLayers.Length; i++) {
            GenerateLevel(i);
        }
    }

    /// <summary>
    /// Generates a layer of the level using the Texture2D layer in
    /// levelLayers and the tileMapComponent 
    /// </summary>
    /// <param name="index">The index of the given layer.</param>
    private void GenerateLevel(int index) {
        //Cycle through the pixels in the level:
        //first cycle through the x pixels (columns)
        for (int x = 0; x < levelLayers[index].width; x++) {
            //for every x pixel (column) cycle through the y pixels (rows)
            for (int y = 0; y < levelLayers[index].height; y++)
            {
                //generate a tile using this pixel position
                GenerateTile(x, y, index);
            }
        }
    }

    /// <summary>
    /// Places the <paramref name="tileEntity"/>'s tile at the given
    /// (x,y) position if the passed in pixelColor matches the
    /// <paramref name="tileEntity"/>'s color.
    /// </summary>
    /// <param name="tileEntity">The Entity to instantiate.</param>
    /// <param name="pixelColor">The pixelColor to match with the Entity's 
    /// color.</param>
    /// <param name="x">The x coordinate of the pixel's position.</param>
    /// <param name="y">The y coordinate of the pixel's position.</param>
    /// <param name="index">The index of the current layer.</param>
    private void placeTileIfMatch(TileEntity tileEntity, 
                                    Color32 pixelColor, int x, int y, int index) {
        //if the tileEntity's color matches the pixelColor
        if (tileEntity.color.Equals(pixelColor)) {
            //convert the pixel position into a tilemap position
            Vector3Int position = new Vector3Int(x, y, 0);
            //place the tileEntity's tile in the Tilemap
            tilemapComponents[index].SetTile(position, tileEntity.tile);
        }
    }

    /// <summary>
    /// Generates the tile using the color of the passed-in coordinate
    /// </summary>
    /// <param name="x">The x coordinate.</param>
    /// <param name="y">The y coordinate.</param>
    /// <param name="index">The index of the current layer</param>
    private void GenerateTile(int x, int y, int index) {
        Color32 pixelColor = levelLayers[index].GetPixel(x, y);

        if (pixelColor.a == 0) {
            //ignore transparent pixels
            return;
        }

        //end of middle section