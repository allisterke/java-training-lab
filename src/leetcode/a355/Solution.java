package leetcode.a355;

import java.util.*;

class Twitter {

    static class Tweet implements Comparable<Tweet> {
        int timestamp;
        int id;

        public Tweet(int timestamp, int id) {
            this.timestamp = timestamp;
            this.id = id;
        }

        @Override
        public int compareTo(Tweet o) {
            return Integer.compare(timestamp, o.timestamp);
        }
    }

    Map<Integer, Set<Integer>> followees = new HashMap<>();
    Map<Integer, List<Tweet>> tweets = new HashMap<>();
    int timestamp = 0;

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(new Tweet(timestamp ++, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Queue<Tweet> feed = new PriorityQueue<>();
        for(int followee : followees.getOrDefault(userId, Collections.emptySet())) {
            get10MostRecentTweetsToFeed(followee, feed);
        }
        get10MostRecentTweetsToFeed(userId, feed);
        Integer[] newsFeed = new Integer[feed.size()];
        int i = newsFeed.length - 1;
        while (!feed.isEmpty()) {
            newsFeed[i --] = feed.poll().id;
        }
        return Arrays.asList(newsFeed);
    }

    void get10MostRecentTweetsToFeed(int userId, Queue<Tweet> feed) {
        List<Tweet> ts = tweets.getOrDefault(userId, Collections.emptyList());
        for(int i = Math.max(0, ts.size() - 10); i < ts.size(); ++ i) {
            feed.add(ts.get(i));
        }
        while (feed.size() > 10) {
            feed.poll();
        }
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId != followerId) {
            followees.putIfAbsent(followerId, new HashSet<>());
            followees.get(followerId).add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId != followerId) {
            followees.getOrDefault(followerId, Collections.emptySet()).remove(followeeId);
        }
    }
}

public class Solution {


    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}