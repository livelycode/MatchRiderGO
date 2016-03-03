//
//  Ride.swift
//  MatchRiderGO
//
//  Created by Leye on 01.03.16.
//  Copyright (c) 2016 LivelyCode. All rights reserved.
//

import UIKit

class Ride {
    var name: String
    var photo: UIImage?
    var rating: Int
    
    init(name: String) {
        self.name = name
        self.rating = 0
    }
}