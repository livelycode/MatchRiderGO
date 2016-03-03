//
//  LocationBadge.swift
//  MatchRiderGO
//
//  Created by Leye on 03.03.16.
//  Copyright (c) 2016 LivelyCode. All rights reserved.
//

import UIKit

class LocationBadge: UILabel {
    required init(coder aDecoder: NSCoder) {
        super.init(coder:aDecoder)
        self.setup()
    }
    
    override init(frame:CGRect) {
        super.init(frame:frame)
        self.setup()
    }
    
    override  func awakeFromNib() {
        super.awakeFromNib()
        self.setup()
    }
    override class func layerClass() -> AnyClass {
        return CATextLayer.self
    }
    
    func textLayer() -> CATextLayer {
        return self.layer as CATextLayer
    }
    
    func setup() {
        self.text = self.text
        self.layer.masksToBounds = true
        self.layer.cornerRadius = 5
        
        self.textLayer().alignmentMode = kCAAlignmentJustified
        self.textLayer().wrapped = true
        self.layer.display()
    }
    
}
