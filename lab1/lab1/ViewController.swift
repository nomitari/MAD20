//
//  ViewController.swift
//  lab1
//
//  Created by Naomi Tari on 9/4/20.
//  Copyright © 2020 Naomi Tari. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var second_selection = false
    var first_pick = -1

    @IBAction func doorSelect(_ sender: UIButton) {
        if !second_selection {
            second_selection = true
            first_pick = sender.tag
//            sleep(1)
            
            if sender.tag == 0 {
                door2.image = UIImage(named: "open_door_goat")
            }
            if sender.tag == 1 {
                door3.image = UIImage(named: "open_door_goat")
            }
            if sender.tag == 2 {
                door1.image = UIImage(named: "open_door_goat")
            }
            
            instruction.text = "To make things interesting, I have opened one of the two doors you did not pick. You have the option to switch your selection, or you can keep your original choice. Click on your final door selection."
        }
        else if second_selection {
            var door = ""
            var conclusion = ""
            
            if first_pick == sender.tag {
                door = "open_door_goat"
                conclusion = "Sorry, you did not win a car. Enjoy your goat."
            }
            else {
                door = "open_door_car"
                conclusion = "Congratulations, you have won a car. Enjoy your prize."
            }
            
            if sender.tag == 0 {
                door1.image = UIImage(named: door)
            }
            if sender.tag == 1 {
                door2.image = UIImage(named: door)
            }
            if sender.tag == 2 {
                door3.image = UIImage(named: door)
            }
            
            instruction.text = conclusion
        }
    }
   
    @IBOutlet weak var instruction: UITextView!
    
    @IBOutlet weak var door1: UIImageView!
    
    @IBOutlet weak var door2: UIImageView!
    
    @IBOutlet weak var door3: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

