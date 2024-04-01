import type { Meta, StoryObj } from '@storybook/react';

import PlusButton from './PlusButton';

const meta = {
  title: 'Common/PlusButton',
  component: PlusButton,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    label: {
      description: '버튼의 텍스트입니다.',
    },
  },
} satisfies Meta<typeof PlusButton>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    label: '아이 추가로 회원가입하기',
  },
};
