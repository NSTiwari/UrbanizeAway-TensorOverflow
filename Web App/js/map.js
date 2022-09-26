function updateMap()
{
	fetch("data.json")
	.then(response => response.json())
	.then(rsp => {
		console.log(rsp)
		rsp.forEach(element => {
			latitude = element.latitude;
			longitude = element.longitude;

			index = element.quantity;
			if(index > 0 && index < 50)
			{
				color = "rgb(255, 0, 0)";
			}
			else if(index >= 50 && index <80 )
			{
				color ="rgb(0, 255, 0)";
			}
			else
			{
				color ="rgb(255, 255, 0)";
			}


			// Mark on the map.
			new mapboxgl.Marker({
			draggable: false,
			color: color
			})
			.setLngLat([longitude, latitude])
			.addTo(map);

		});

	})
}

updateMap();