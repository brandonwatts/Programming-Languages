#lang racket

(define (constructBST) '())

(define (mapBST bst ftoapply)
  (if (null? bst)
      '()
       (list (car bst) (ftoapply (cadr bst)) (mapBST (caddr bst) ftoapply) (mapBST (cadddr bst) ftoapply))
  )
)

(define (addToBST BST k v)
  (if (null? BST)
       (list k v '() '())
   (if (> (car BST) k)
       (list (car BST) (cadr BST) (addToBST (caddr BST) k v) (cadddr BST))
       (list (car BST) (cadr BST) (caddr BST) (addToBST (cadddr BST) k v))
    )
   )
)

(define bst (addToBST (addToBST (addToBST (addToBST (constructBST) 3 "3") 1 "1") 2 "2") 5 "5")) 

(define (searchBST BST k)
  (if (null? BST)
       (list k)
       (if(= (car BST) k)
          (list (car BST) (cadr BST))
          (if (> (car BST) k)
              (searchBST (caddr BST) k)
              (searchBST (cadddr BST) k)
          )
       )
   )
)

(define (foldBST BST someFunct)
  (if (null? BST)
       (someFunct '() '() '() '())
       (someFunct (car BST) (cadr BST) (foldBST (caddr BST) someFunct) (foldBST (cadddr BST) someFunct))
   )
)

;;; BELOW ARE SOME EXAMPLES OF HOW YORU CODE WILL BE TESTED

;;; A COUPLE OF FUNCTIONS WE DEFINE FOR TESTS

(define (addAllKeys k v lfv rfv)
  (if (null? k) ; no need to check if v,lfv,rfv are also '(); if k is '(), it must have been a call (addAllKeys '() '() '() '())
      0 ; if called with null node, return 0
      (+ k lfv rfv) ; otherwise, add node's key to left-fold-value (sum of keys from left subtree) and right-fold-value (sum of keys from right subtree))
  )
)
(define (minimumKey k v lfv rfv)
  (if (null? k)
      '()
      (if (null? lfv)
          k
          lfv
      )
  )
)
(define (flattenTree k v lfv rfv)
  (if (null? k)
      '()
      (append lfv (list k v)  rfv)
  )
)
(define (concatStringValuesPreOrder k v lfv rfv)
  (if (null? k)
      ""
      (string-append v lfv rfv)
  )
)
(define (concatStringValuesInOrder k v lfv rfv)
  (if (null? k)
      ""
      (string-append lfv v rfv)
  )
)

;;; AND NOW TESTING IF THESE FUNCTIONS WERE CALLED PROPERTLY BY YOUR CODE

(searchBST bst 5) ; should return: '(5 "5")
(searchBST bst 6) ; should return: '(6)
(mapBST bst string->number) ; should return: '(3 3 (1 1 () (2 2 () ())) (5 5 () ()))
(foldBST bst addAllKeys ) ; should return: 11
(foldBST bst minimumKey ) ; should return: 1
(foldBST bst flattenTree ) ; should return: '(1 "1" 2 "2" 3 "3" 5 "5")
(foldBST bst concatStringValuesPreOrder ) ; should return: "3125"
(foldBST bst concatStringValuesInOrder ) ; should return: "1235"