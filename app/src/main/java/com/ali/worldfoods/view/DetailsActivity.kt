package com.ali.worldfoods.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.ali.worldfoods.R
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var data = intent.extras

        if(data != null)
        {
            var name = data.get("foodName")
            var image = data.get("foodImage")
            var link = data.get("foodLink")

            foodTxtView.text = name.toString()
            Picasso.get().load(image.toString().toUri()).into(foodImgView)

            //Toast.makeText(this, image.toString(), Toast.LENGTH_LONG).show()

            playVideoView.setOnClickListener {
                var intent = YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.developer_key), "${link}",
                    0, true, false);
                startActivity(intent);
            }


        }
    }
}