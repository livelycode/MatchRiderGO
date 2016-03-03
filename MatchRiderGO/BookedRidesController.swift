//
//  FirstViewController.swift
//  MatchRiderGO
//
//  Created by Leye on 01.03.16.
//  Copyright (c) 2016 LivelyCode. All rights reserved.
//

import UIKit

let swiftBlogs = ["Ray Wenderlich", "NSHipster", "iOS Developer Tips", "Jameson Quave", "Natasha The Robot", "Coding Explorer", "That Thing In Swift", "Andrew Bancroft", "iAchieved.it", "Airspeed Velocity"]

let textCellIdentifier = "BookedRideCell"


class BookedRidesController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var bookedRidesTable: UITableView!
    
    
    let items = ["Item 1", "Item2", "Item3", "Item4"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bookedRidesTable.registerNib(UINib(nibName: "RideRowCell", bundle: nil), forCellReuseIdentifier: "BookedRideCell")
    }
    
    // MARK: - UITableViewDataSource
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
     func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("BookedRideCell", forIndexPath: indexPath) as! RideRowCell
        
        cell.driverName.text = items[0]
        cell.startLocation.text = items[0]
        cell.destinationLocation.text = items[0]
        
        return cell
    }
    
    func tableView(tableView: UITableView, didDeselectRowAtIndexPath indexPath: NSIndexPath) {
        performSegueWithIdentifier("test", sender: self)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

