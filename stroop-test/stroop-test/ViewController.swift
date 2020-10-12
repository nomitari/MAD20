//
//  ViewController.swift
//  stroop-test
//
//  Created by Naomi Tari on 10/10/20.
//  Copyright © 2020 Naomi Tari. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var instructions: UILabel!
    @IBOutlet weak var color: UIButton!
    @IBOutlet weak var square0: UIButton!
    @IBOutlet weak var square1: UIButton!
    @IBOutlet weak var square2: UIButton!
    @IBOutlet weak var square3: UIButton!
    @IBOutlet weak var exitButton: UIButton!
    
    let colorStrings: [String] = ["red", "orange", "yellow", "green", "blue", "purple", "pink", "black", "gray", "brown"]
    let UIColors: [UIColor] = [UIColor.red, UIColor.orange, UIColor.yellow, UIColor.green, UIColor.blue, UIColor.purple, UIColor.systemPink, UIColor.black, UIColor.gray, UIColor.brown]
    var results: [Int] = []
    var rounds : Int = 0
    //  https://www.hackingwithswift.com/articles/117/the-ultimate-guide-to-timer
    var timer = Timer()
    
    //  called on click exit from inside game
    @IBAction func exit(_ sender: UIButton) {
        timer.invalidate()
        color.setTitle("PLAY", for: .normal)
        color.isEnabled = true
        formatPlayButton()
        
        instructions.text = "Select the color below that is represented\nby the written word. You have two\nseconds to make your selection!"
        
        sender.isHidden = true
    }
    
    //  called on click play, controls timer
    @IBAction func play(_ sender: UIButton) {
        results = []
        color.isEnabled = false
        exitButton.isHidden = false
        
        timer = Timer.scheduledTimer(timeInterval: 1.5, target: self, selector: #selector(playRound), userInfo: nil, repeats: true)
    }
    
    //  runs a single round of the test
    @objc func playRound() {
        if rounds == 10 {
            timer.invalidate()
            showResults()
            rounds = 0
            return
        }
        
        rounds += 1
        instructions.text = ""
        
        //  https://stackoverflow.com/questions/24003191/pick-a-random-element-from-an-array
        let wordText = colorStrings.randomElement()
        let wordColor = UIColors.randomElement()
        
        //  https://stackoverflow.com/questions/26641571/how-to-change-button-text-in-swift-xcode-6
        color.setTitle(wordText, for: .normal)
        //  https://stackoverflow.com/questions/25170421/changing-color-of-button-text-and-state/43321844
        color.setTitleColor(wordColor, for: UIControl.State.normal)
        
        //  https://www.hackingwithswift.com/articles/102/how-to-generate-random-numbers-in-swift
        let correctSquare = Int.random(in: 0...3)
        
        //  set random colors for all square buttons
        randomizeTiles(correctTag: correctSquare, colorString: wordText!)
    }
    
    //  randomly colors tiles
    //  https://stackoverflow.com/questions/37305951/default-optional-parameter-in-swift-function
    func randomizeTiles(correctTag: Int = -1, colorString: String = "") {
        let colorButtons: [UIButton] = [square0, square1, square2, square3]
        var usedColors: [String] = [colorString]
        
        //  https://stackoverflow.com/questions/26837371/how-to-change-uibutton-image-in-swift
        for square in colorButtons {
            
            //  don't want to repeat colors in a round
            var randColor = colorStrings[Int.random(in: 0...9)]
            while usedColors.contains(randColor) {
                randColor = colorStrings.randomElement()!
            }
            usedColors.append(randColor)
            square.setImage(UIImage(named: randColor), for: UIControl.State.normal)
            //  if correct square given
            if correctTag >= 0 && colorString != "" {
                //  if at correct square
                if square.tag == correctTag {
                    //  set random square to be correct answer
                    square.setImage(UIImage(named: colorString), for: UIControl.State.normal)
                }
            }
        }
    }
    
    //  when a colored tile is clicked, keeps track of result
    @IBAction func colorClick(_ sender: UIButton) {
        //  https://stackoverflow.com/questions/31975858/get-button-image-file-name-in-swift
        //  append 1 to results if correct
        if sender.currentImage == UIImage(named: color.currentTitle!) {
            results.append(1)
        }
    }
    
    //  once 10 rounds are completed, displays score
    func showResults(){
        //  https://developer.apple.com/documentation/swift/array/2298686-reduce
        //  sum of positive results * 10 to get percentage
        var score = results.reduce(0, { x, y in
            x + y
        })
        score = score * 10
        //  show score and change button to "play again"
        instructions.text = "Final score: \(score)%"
        
        color.isEnabled = true
        color.setTitle("PLAY AGAIN", for: .normal)
        
        formatPlayButton()
    }
    
    //  called on start and restart states
    func formatPlayButton() {
        color.setTitleColor(UIColor.black , for: UIControl.State.normal)
        color.isEnabled = true
        //color.backgroundColor = UIColor.lightGray
        //color.layer.cornerRadius = 8
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        formatPlayButton()
        randomizeTiles()
        exitButton.isHidden = true
    }
}

