//
//  ViewController.swift
//  lab 3
//
//  Created by Naomi Tari on 9/24/20.
//

import UIKit

class ViewController: UIViewController {

    var defaultcap = "this is a cat."
    var cat1cap = "this is a small cat."
    var cat2cap = "this is a fat cat."
    var cat3cap = "this is the fattest cat."
    
    var capsOn = false
    var verboseOn = false
    
    @IBOutlet weak var cat: UIImageView!
    
    @IBOutlet weak var cat_cap: UILabel!
    
    @IBOutlet weak var weight: UISegmentedControl!
    
    @IBAction func change_weight(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            cat.image = UIImage(named: "cat1")
            cat_cap.text = ( !verboseOn ? defaultcap : cat1cap )
            cat_cap.text = ( !capsOn ? String(cat_cap.text!).lowercased() : String(cat_cap.text!).uppercased() )
        }
        else if sender.selectedSegmentIndex == 1 {
            cat.image = UIImage(named: "cat2")
            cat_cap.text = ( !verboseOn ? defaultcap : cat2cap )
            cat_cap.text = ( !capsOn ? String(cat_cap.text!).lowercased() : String(cat_cap.text!).uppercased() )
        }
        else if sender.selectedSegmentIndex == 2 {
            cat.image = UIImage(named: "cat3")
            cat_cap.text = ( !verboseOn ? defaultcap : cat3cap )
            cat_cap.text = ( !capsOn ? String(cat_cap.text!).lowercased() : String(cat_cap.text!).uppercased() )
        }
    }
    
    @IBAction func verbose(_ sender: UISwitch) {
        if sender.isOn { verboseOn = true }
        else { verboseOn = false }
        if weight.selectedSegmentIndex == 0 {
            if verboseOn {
                cat_cap.text = ( !capsOn ? cat1cap : cat1cap.uppercased() )
            }
            else {
                cat_cap.text = ( !capsOn ? defaultcap : defaultcap.uppercased() )
            }
        }
        else if weight.selectedSegmentIndex == 1 {
            if verboseOn {
                cat_cap.text = ( !capsOn ? cat2cap : cat2cap.uppercased() )
            }
            else {
                cat_cap.text = ( !capsOn ? defaultcap : defaultcap.uppercased() )
            }
        }
        else if weight.selectedSegmentIndex == 2 {
            if verboseOn {
                cat_cap.text = ( !capsOn ? cat3cap : cat3cap.uppercased() )
            }
            else {
                cat_cap.text = ( !capsOn ? defaultcap : defaultcap.uppercased() )
            }
        }
    }
    
    @IBAction func caps(_ sender: UISwitch) {
        if sender.isOn { capsOn = true }
        else { capsOn = false }
        if capsOn {
            cat_cap.text = String(cat_cap.text!).uppercased()
        }
        else {
            cat_cap.text = String(cat_cap.text!).lowercased()
        }
    }
    
    @IBAction func color(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            cat_cap.textColor = UIColor.black
        }
        else if sender.selectedSegmentIndex == 1 {
            cat_cap.textColor = UIColor.green
        }
    }

    @IBAction func size(_ sender: UISlider) {
        var size_val : CGFloat
        size_val =  CGFloat(10 + sender.value * 15)
//        size_val = CGFloat(sender.value)
//        cat_cap.font = cat_cap.font.withSize(CGFloat(size_val))
        cat_cap.font = UIFont.systemFont(ofSize:size_val)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

