//
//  LocationBadge.m
//  MatchRiderGO
//
//  Created by Leye on 29.02.16.
//  Copyright (c) 2016 LivelyCode. All rights reserved.
//

#import "LocationBadge.h"

@implementation LocationBadge

- (id)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        self.edgeInsets = UIEdgeInsetsMake(5, 10, 5, 10);
    }
    return self;
}


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    [super drawTextInRect:UIEdgeInsetsInsetRect(rect, self.edgeInsets)];
}



@end
