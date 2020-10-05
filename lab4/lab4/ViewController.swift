//
//  ViewController.swift
//  lab4
//
//  Created by Naomi Tari on 10/5/20.
//  Copyright © 2020 Naomi Tari. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var textField: UITextField!
    
    @IBOutlet weak var stepper: UIStepper!
        
    @IBOutlet weak var finalNumber: UILabel!
    
    @IBAction func adjustNumber(_ sender: UIStepper) {
        let stepperChange = Int(sender.value)
        updateNumber(stepperVal: stepperChange)
    }
    
    func updateNumber(stepperVal : Int) {
        guard let number = Int(textField.text!) else {
                let alert=UIAlertController(title: "Warning", message: "Please enter a valid number", preferredStyle: UIAlertController.Style.alert)
                let okAction=UIAlertAction(title: "OK", style:UIAlertAction.Style.default, handler: nil)
                alert.addAction(okAction)
                present(alert, animated: true, completion: nil)
                return
        }
        
        let finalNum = number + stepperVal
        finalNumber.text = String(finalNum)
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateNumber(stepperVal: 0)
        // reset stepper val
        stepper.value = 0
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func viewDidLoad() {
        textField.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
        super.touchesBegan(touches, with: event)
    }

}

