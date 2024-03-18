import type { Meta, StoryObj } from '@storybook/react';

import Tag from './Tag';

const meta = {
  title: 'Common/Tag',
  component: Tag,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    tags: {
      description: 'name과 onClick를 가진 tag 객체 배열입니다.',
    },
  },
} satisfies Meta<typeof Tag>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    tags: [
      {
        label: '1일',
        onClick: () => console.log('HTML tag clicked'),
      },
      {
        label: '1주',
        onClick: () => console.log('HTML tag clicked2'),
      },
    ],
  },
};
