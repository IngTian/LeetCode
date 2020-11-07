//
// Created by Olina Yu on 2020-11-07.
//

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class BST {
    TreeNode *root;
public:
    void addNode(TreeNode *root, int val) {
        if (root == nullptr) return new TreeNode(val);
        else if (root->val > val) root->left = addNode(root->left, val);
        else if (root->val < val) root->right = addNode(root->right, val);
        return root;
    }

    void deleteNode(TreeNode *root, int val) {
        if (root != nullptr) {

            if (root->val == key) {
                // Found

                TreeNode *leftSubNode = root->left, *rightSubNode = root->right;
                if (leftSubNode == nullptr && rightSubNode == nullptr) root = nullptr;
                else if (leftSubNode == nullptr) root = rightSubNode;
                else if (rightSubNode == nullptr) root = leftSubNode;
                else {
                    // Both sub nodes are not empty.
                    // We take the right sub-node to be
                    // the new root, and insert the left
                    // sub-node into the leftest part of
                    // right sub tree.
                    TreeNode *pointer = rightSubNode;
                    root = rightSubNode;
                    while (pointer->left != nullptr) pointer = pointer->left;
                    pointer->left = leftSubNode;
                }
            } else if (root->val > key) {
                // The target should be in the left sub tree.
                root->left = deleteNode(root->left, key);
            } else if (root->val < key) {
                // The target should be in the right sub tree.
                root->right = deleteNode(root->right, key);
            }
        }
        return root;
    }

    TreeNode *search(TreeNode *root, int val) {
        if (root == nullptr) return root;
        else if (root->val > val) return search(root->left, val);
        else if (root->val < val) return search(root->right, val);
        else if (root->val == val) return root;
        return nullptr;
    }
};