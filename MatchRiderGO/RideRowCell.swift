//
//  RideRowViewTableViewCell.swift
//  MatchRiderGO
//
//  Created by Leye on 01.03.16.
//  Copyright (c) 2016 LivelyCode. All rights reserved.
//

import UIKit

class RideRowCell: UITableViewCell {

    @IBOutlet weak var driverName: UILabel!
    
    @IBOutlet weak var startLocation: UILabel!
    @IBOutlet weak var destinationLocation: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
