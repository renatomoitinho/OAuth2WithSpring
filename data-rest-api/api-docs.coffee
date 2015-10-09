  title: "Polls API"
  info: "Polls is a simple API allowing consumers to view polls and vote in them."
  mappings:
    title: "Questions Collection"
    routes:

      title: "List All Question"
      method: "GET"
      url: "/questions"
      response: [
        status: 200
        content: '[{"question":"Favourite programming language?","published_at":"2015-08-05T08:40:51.620Z","choices":[{"choice":"Swift","votes":2048},{"choice":"Python","votes":1024},{"choice":"Objective-C","votes":512},{"choice":"Ruby","votes":256}]}]'
       ]

      title: "Create a new Question"
      method: "POST"
      url: "/questions"
      response: [
        status: 200
      ,
        status: 401
        content: '{"error":"unauthorized"}'
      ]

