using UnityEngine;

[System.Serializable]
public class ColorToPrefab
{

	public Color color;
	public GameObject prefab;

	public ColorToPrefab(Color color)
	{
		this.color = color;
	}

}