package ads.pdm.mycontacts.Adapter

import ads.pdm.mycontacts.Model.Contact
import ads.pdm.mycontacts.R
import ads.pdm.mycontacts.databinding.TileContactBinding
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactAdapter(
    context: Context,
    val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {

    private lateinit var tcb: TileContactBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList.get(position)
        var contactTileView = convertView

        if (contactTileView == null){
            //inflo uma nova celula
            tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root
        }
        tcb.nameTv.text = contact.name
        tcb.emailTv.text = contact.email
        return contactTileView
    }
}