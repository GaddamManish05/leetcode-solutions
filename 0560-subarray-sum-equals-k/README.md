<h2><a href="https://leetcode.com/problems/subarray-sum-equals-k">560. Subarray Sum Equals K</a></h2><h3>Medium</h3><hr><p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the total number of subarrays whose sum equals to</em> <code>k</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1], k = 2
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3], k = 3
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>


// Prefix Sum Concept:
//
// Let:
// currentSum = sum of elements from index 0 to i.
//
// We need a subarray whose sum is k.
//
// Formula:
// subarraySum = currentSum - previousPrefixSum
//
// Therefore,
// currentSum - previousPrefixSum = k
//
// Rearranging,
// previousPrefixSum = currentSum - k
//
// So, if (currentSum - k) has already appeared as a prefix sum,
// then every occurrence of that prefix sum forms a valid subarray
// ending at the current index.
//
// HashMap stores:
// Key   -> Prefix Sum
// Value -> Frequency of that Prefix Sum
//
// Frequency is stored because the same prefix sum may occur multiple
// times, and each occurrence represents a different valid starting point.
//
// Initializing map with (0 -> 1) represents an empty prefix before
// the array starts, allowing us to count subarrays that begin at index 0.
