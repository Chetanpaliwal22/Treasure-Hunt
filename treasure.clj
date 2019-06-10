(ns clojure.examples.treasure
    (:gen-class))
    (use '[clojure.string :only (split triml)])
    
    (def graph (atom()))

    (defn getInput []
				(def graph (slurp "map.txt"))
			(println "Input File:")
		(println graph)
			(def graph2 graph)
			 (if (clojure.string/blank? graph2) ( ( println "Input file is not correct")(System/exit 0)))
       
	(with-open [rdr (clojure.java.io/reader "map.txt")]
		(def row (count (line-seq rdr))))

	;;Code for number of col

	(with-open [rdrcol1 (clojure.java.io/reader "map.txt")]
		(doseq [line1 (line-seq rdrcol1)]
			(def lineGlobal line1)))
		(def col (count lineGlobal))


		(with-open [rdrcol (clojure.java.io/reader "map.txt")]
		(doseq [line (line-seq rdrcol)]

		(if (not= col (count line)) ( ( println "Input file is not correct")(System/exit 0)))
		))

	;;Code to get input into a 2 D Array

	(def graph (to-array-2d (partition col (filter #(not (clojure.string/blank? %))(split graph #"")))))
	(if (not= row (alength graph))
			(do
			(println "Input file is not correct")
		  (System/exit 0))
	)		

		(print "Row: ")
(println row)
(print "Col: ")
		(println col)
		)
	;;Code to print 2d array
	;;(doseq [x (range row)]
	;;	(doseq [y (range col)]
	;;					 (print (str (aget graph x y)))) (println ""))
	;;	
	;;	)
		
	(defn dfs [[i j]]
	
	;;(println (aget graph 1 1))
	;;(println "In the dfs method -------")
	;;(println i)
	;;(println j)

;;(println (aget graph 0 0))
(if (< i 0) ((println "Uh oh I could not find the treasure :-")(System/exit 0)))
(if (< j 0) ((println "Uh oh I could not find the treasure :-")(System/exit 0)))

(def currInput "x")
	
(if (and (< i row) (< j col)) (if (and (>= i 0) (>= j 0))(def currInput (str (aget graph i j)))(def currInput "Y")))

;;Exit Condition for # @ (0,0)
(def compResult2 (compare currInput "#"))
(if (= compResult2 0) ((println "Uh oh, I could not find the treasure :-")(doseq [x (range row)]
	(doseq [y (range col)]
					 (print (str (aget graph x y)))) (println ""))(System/exit 0)))
;;Condition Over


	;;Base Condition
	(def compResult (compare currInput "@"))
	(if (= compResult 0) ((println "Woo hoo, I found the treasure :-")
	(doseq [x (range row)]
		(doseq [y (range col)]
						 (print (str (aget graph x y)))) (println ""))
				(System/exit 0)))	
	;;Base Condition Over
	
	;;Right Condition Check for @
	(def inJ (+ j 1))
	(if (< inJ col)(def chckInput (str (aget graph i inJ))))
	(def compResult (compare chckInput "@"))
	(if (and (< inJ col) (= compResult 0)) ((print "Right Side @ ")(aset graph i j "+")(dfs[i, inJ])))
		
	;;Bottom Condition Check for @
	(def inI (+ i 1))
	(if (< inI row)(def chckInput (str (aget graph inI j))))
	(def compResult (compare chckInput "@"))
	(if (and (< inI row) (= compResult 0)) ((print "")(aset graph i j "+")(dfs[inI, j])))
	
	;;Top Condition Check for @
	(def decI (- i 1))
	(if (> decI -1)(def chckInput (str (aget graph decI j))))
	(def compResult (compare chckInput "@"))
	(if (and (> decI -1) (= compResult 0)) ((print "")(aset graph i j "+")(dfs[decI, j])))
	
	;;Left Condition Check for @
	(def decJ (- j 1))
	(if (> decJ -1)(def chckInput (str (aget graph i decJ))))
	(def compResult (compare chckInput "@"))
	(if (and (> decJ -1) (= compResult 0)) ((print "")(aset graph i j "+")(dfs[i, decJ])))

	;;Right Side with '-' value
	(def inJ (+ j 1))
	(if (< inJ col)(def chckInput (str (aget graph i inJ))))
	(def compResult (compare chckInput "-"))
	(if (and (< inJ col) (= compResult 0)) 
	((print "")(aset graph i j "+")(dfs[i, inJ])))

	
	;;Bottom Side with '-' value
	(def inI (+ i 1))
	(if (< inI row)(def chckInput (str (aget graph inI j))))
	(def compResult (compare chckInput "-"))
	(if (and (< inI row) (= compResult 0)) 
	((print "")(aset graph i j "+")(dfs[inI, j])))
	
	;;Top Side with '-' value
	(def decI (- i 1))
	(if (> decI -1)(def chckInput (str (aget graph decI j))))
	(def compResult (compare chckInput "-"))
	(if (and (> decI -1) (= compResult 0)) 
	((print "")(aset graph i j "+")(dfs[decI, j])))
	
	;;Left Side with '-' value
	(def decJ (- j 1))
	(if (> decJ -1)(def chckInput (str (aget graph i decJ))))
	(def compResult (compare chckInput "-"))
	(if (and (> decJ -1) (= compResult 0)) 
	((print "")(aset graph i j "+")(dfs[i, decJ])))
	

	;;Right Side with '+' value
	(def inJ (+ j 1))
	(if (< inJ col)(def chckInput (str (aget graph i inJ))))
	(def compResult (compare chckInput "+"))
	(if (and (< inJ col) (= compResult 0)) 
	((print "")(aset graph i j "!")(dfs[i, inJ])))
	
	;;Bottom Side with '+' value
	(def inI (+ i 1))
	(if (< inI row)(def chckInput (str (aget graph inI j))))
	(def compResult (compare chckInput "+"))
	(if (and (< inI row) (= compResult 0)) 
	((print "")(aset graph i j "!")(dfs[inI, j])))
	
	;;Top Side with '+' value
	(def decI (- i 1))
	(if (> decI -1)(def chckInput (str (aget graph decI j))))
	(def compResult (compare chckInput "+"))
	(if (and (> decI -1) (= compResult 0)) 
	((print "")(aset graph i j "!")(dfs[decI, j])))
	
	;;Left Side with '+' value
	(def decJ (- j 1))
	(if (> decJ -1)(def chckInput (str (aget graph i decJ))))
	(def compResult (compare chckInput "+"))
	(if (and (> decJ -1) (= compResult 0)) 
	((print "")(aset graph i j "!")(dfs[i, decJ])))
	

	;;Final Condition for '!'
	(if (and (< i row) (< j col)) (if (and (>= i 0) (>= j 0))(def currInput (str (aget graph 0 0)))))
	(def compResult (compare currInput "+"))
	(if (= compResult 0) (aset graph 0 0 "!")
				)	
;;Final Condition for '!'
				(if (and (< i row) (< j col)) (if (and (>= i 0) (>= j 0))(def currInput (str (aget graph 0 0)))))
				(def compResult (compare currInput "-"))
				(if (= compResult 0) (aset graph 0 0 "!")
							)	
	;;Base Condition Over

	;;Final Condition
	(println "Uh oh, I could not find the treasure :-")
	(doseq [x (range row)]
		(doseq [y (range col)]
						 (print (str (aget graph x y)))) (println ""))
	(System/exit 0)
	;;Final Condition Over
	
	)

    ;;Function Call
   
  (getInput)
  ;;(printOutput)
  (dfs[0 0])
  
  